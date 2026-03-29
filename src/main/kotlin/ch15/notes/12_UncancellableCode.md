### 协程微观执行流与内存状态流转拆解 (基于挂起点的协作式取消)

核心流程：C1 触发 `cancel` -> C2 状态瞬间变 `Cancelling` -> C2 被塞入就绪队列等待 -> **C1 继续跑完自己剩下的同步代码** -> C1 结束交出线程 -> EventLoop 从就绪队列掏出 C2 -> C2 拿到线程，发现自己处于 `Cancelling` 并在挂起点抛出异常。

```kotlin
suspend fun doCpuHeavyWork(): Int {
    log("I'm doing work!")
    var counter = 0
    val startTime = System.currentTimeMillis()
    while (System.currentTimeMillis() < startTime + 500) {
        counter++ // <1> 通过递增计数器 500 毫秒来模拟某些 CPU 密集型计算
        delay(100.milliseconds) // <2> 此函数调用还引入了一个可以取消 doCpuHeavyWork 的点。
    }
    return counter
}

fun main() {
    runBlocking {
        val myJob = launch {
            repeat(5) {
                log(doCpuHeavyWork())
            }
        }
        delay(600.milliseconds)
        myJob.cancel()
    }
}
/* Output:
0 [main @coroutine#2] I'm doing work!
559 [main @coroutine#2] 5
560 [main @coroutine#2] I'm doing work!
 */
```

为精确分析挂起与恢复的底层物理动作，定义当前环境的物理实体与状态：
* **调度环境**：未指定 Dispatcher，默认使用 `runBlocking` 内部初始化的单线程 `BlockingEventLoop`。主线程循环执行三个核心步骤：1. 检查延时队列（Delayed Queue，基于时间戳的最小堆）；2. 将到期任务移入就绪队列（Ready Queue）；3. 提取就绪队列头节点执行，若全空则休眠（Park）。
* **C1**：`runBlocking` 创建的根协程，物理实体为 `BlockingCoroutine` (兼任 Job 1 和 Continuation)。
* **C2**：`launch` 创建的子协程，物理实体为 `StandaloneCoroutine` (兼任 Job 2 和 Continuation)。

---

#### 第一阶段：初始化与 C1 的主动挂起 (T = 0ms)

1. **C1 入栈与 C2 创建**：主线程进入 `BlockingEventLoop`。C1 执行到 `launch`，在堆内存中实例化 `StandaloneCoroutine` (C2)，建立父子 Job 的双向链表关联。
2. **C2 入队就绪**：底层调用 `C2.context.dispatcher.dispatch`，将 C2 压入 EventLoop 的 Ready Queue。
3. **C1 挂起并交出线程**：C1 执行到 `delay(600)` 准备挂起自身并让出主线程控制权。
   * **状态校验**：delay 调用底层 `suspendCancellableCoroutine` API。在真正让出线程前，引擎第一步会读取 C1 自身的 `_state` 指针，确认其仍处于 `Active` 状态。
   * **物理入队**：校验通过后，计算绝对时间戳 (当前纳秒 + 600ms)，将 C1 的状态机指针封装为 `DelayedResumeTask`，压入 EventLoop 的 Delayed Queue 延时队列最小堆中。
   * **状态保存与让出**：C1 保存局部变量，向主线程调用栈返回 `COROUTINE_SUSPENDED` 标志位。主线程将 C1 的栈帧弹出，控制权回退至 EventLoop 的底层 `while(true)` 轮询机制中。

#### 第二阶段：C2 业务循环与 EventLoop 精准调度 (T = 1ms ~ 549ms)

1. **EventLoop 轮询原则 & C2 首次提取**：主线程回到 EventLoop 后，严格遵循“先查时间，后查任务”的原则。首先检查延时队列，发现 C1 (T=600ms) 未到期；随后检查就绪队列，发现 Ready Queue 存在 C2，主线程提取 C2 并调用 `resumeWith` 压栈执行。
2. **第一次 repeat 执行**：C2 进入 `doCpuHeavyWork()`，输出 `0 [main @coroutine#2] I'm doing work!`。进入 `while` 循环，`counter` 递增为 1。
3. **C2 遇到挂起点 (delay 100ms)**：C2 执行到 `delay(100.milliseconds)`。

   * **状态校验**：`delay` 底层同样调用 `suspendCancellableCoroutine`。在真正交出线程前，第一步检查 `C2._state` 字段，确认当前状态为 `Active`。
   * **物理入队**：计算绝对时间戳 (当前纳秒 + 100ms)，将 C2 的 Continuation 包装压入 Delayed Queue。
   * **状态保存与让出**：C2 的状态机保存 `counter=1`，向主线程返回 `COROUTINE_SUSPENDED`。主线程清空调用栈，回到 EventLoop。
4. **EventLoop 轮询空转与物理休眠 (Park)**：控制权退回到 EventLoop 的 `while(true)` 底层循环后，开始依次执行三个核心调度步骤：
   * **1. 检查延时队列**：首先读取延时队列（最小堆）的堆顶元素。此时堆顶任务为 C2（设定唤醒时间 T=100ms），堆内还存在 C1（设定唤醒时间 T=600ms）。比对当前系统时间（T ≈ 1ms），确认堆顶任务 C2 尚未到期，因此**不执行任何出队与转移操作**。
   * **2. 检查就绪队列**：接着读取就绪队列（Ready Queue），确认其内部为空，当前无待执行的协程任务。
   * **3. 计算时间差与休眠**：由于延时队列无到期任务，且就绪队列为空，底层调度器需要挂起线程以避免 CPU 资源无效轮询。底层计算出当前时间距离延时队列堆顶任务（C2）的时间差（约 100ms），并将该纳秒级时间差值传入系统级原语 `LockSupport.parkNanos(nanos)`。主线程在此处交出 CPU 执行权，进入阻塞挂起状态。
5. **C2 恢复执行机制**：
   * **时钟中断与调度恢复**：约 100ms 后，操作系统时钟中断触发，底层调用 `unpark` 唤醒主线程。EventLoop 恢复执行，立刻开启下一轮 `while(true)` 循环：首先执行步骤一检查延时队列。此时比对系统时间发现 C2 已到期，将其从延时队列中移除并追加至就绪队列尾部；随后执行步骤二，从就绪队列提取出 C2，准备调用 `resumeWith` 恢复其执行上下文。
   * **恢复后校验**：C2 状态机通过 `resumeWith` 从挂起点恢复上下文。在 `suspendCancellableCoroutine` 的恢复回调点，底层**第二次检查**当前 `Job` 的状态。确认仍为 `Active` 且未收到异常结果后，将执行权交还给业务代码。
   * C2 恢复 `counter` 值，继续 `while` 循环。
6. **第一轮 repeat 结束**：上述挂起与恢复的物理过程重复约 5 次。此时 `System.currentTimeMillis() < startTime + 500` 判断为 false。跳出 `while` 循环，返回 `counter=5`。第一轮 `repeat` 结束。输出 `549 [main @coroutine#2] 5`。

#### 第三阶段：第二轮 repeat 与伏笔埋设 (T ≈ 549ms)

1. **启动第二轮**：C2 指令流进入 `repeat` 的第二次循环。再次调用 `doCpuHeavyWork()`。
2. **初始化循环状态**：打印 `549 [main @coroutine#2] I'm doing work!`。局部变量 `startTime` 重新获取，`counter` 重置为 0，进入 `while` 后变为 1。指令再次遭遇 `delay(100)`。
3. **状态校验与入队**：C2 再次执行挂起前校验，确认状态为 `Active`。计算唤醒时间戳 (T ≈ 649ms)，将自身压入延时队列，保存状态机并释放主线程。此时，延时队列中存在两个任务：C1 (T=600ms) 和 C2 (T=649ms)。

#### 第四阶段：C1 唤醒与取消指令注入 (T = 600ms)

1. **C1 到期出队**：T=600ms 时钟中断触发，EventLoop 轮询延时队列，发现 C1 (`delay 600`) 已到期。将其移入就绪队列并立刻提取执行。C1 在 `delay(600)` 恢复点检查自身状态为 `Active`，继续向下执行。
2. **执行取消指令**：C1 执行 `myJob.cancel()`。
3. **无锁 CAS 状态跃迁**：底层寻址到 C2 (Job 2) 物理实体。通过无锁 CAS 操作，将 C2 的 `_state` 指针从 `Active` 强行替换为 **`Cancelling`** 状态，并内部实例化一个 `JobCancellationException` 绑定至该状态节点上。
4. **强制唤醒与异常注入**：
   * `cancel` 方法在修改状态后，会顺着内部引用查找 C2 当前的物理位置。扫描 C2 当前的挂起状态，发现 C2 正以 `DelayedResumeTask` 形式驻留在延时队列中等待 649ms 的唤醒。
   * 底层直接将 C2 从延时队列中**物理移除**（或标记为无效），并强制将其以携带失败结果的形式调用 `C2.resumeWith(Result.failure(JobCancellationException))`。由于 C2 物理实体被包装为 `DispatchedContinuation`，此处的 `resumeWith` 底层会触发 `C2.dispatcher.dispatch(...)` 方法，将携带异常状态的 C2 作为任务节点，正式追加至 EventLoop 的就绪队列（Ready Queue）尾部等待调度。
5. **C1 挂起等待**：C1 代码执行完毕，状态变更为 `Completing`。但原子性校验发现子协程 C2 尚未彻底 `Completed`，因此 C1 挂起等待，释放主线程。

#### 第五阶段：挂起点检查拦截与调用栈回溯 (T > 600ms)

1. **C2 携带异常恢复执行**：EventLoop 从就绪队列提取被强制唤醒的 C2。
2. **挂起点内部检查与异常抛出**：
   * C2 的调用栈恢复在 `delay(100)` 内部的 `suspendCancellableCoroutine` 恢复回调处。
   * `delay` 在恢复执行的第一步，**严格校验挂起期间是否收到了失败结果**。
   * 校验确认存在 `Result.failure` 传参，同时读取 C2 的 `_state` 指针发现状态已变为 `Cancelling`。`delay` 函数内部中止正常的业务恢复流程，将接收到的 `JobCancellationException` **向上抛出 (`throw`)**。
3. **调用栈回溯 (Stack Unwinding)**：
   * 异常在 `while` 循环内部抛出。由于业务代码未编写 `try-catch` 拦截，异常导致 `while` 循环中断，`doCpuHeavyWork` 函数栈帧非正常弹出，异常向上传递至 `repeat` 闭包，`repeat` 栈帧弹出。异常最终传递至 `launch` 协程的物理边界。
4. **结构化异常吞没与状态收敛**：
   * `StandaloneCoroutine` (C2) 的基类捕获该异常。类型比对确认属于 `CancellationException`，判定这属于合法的“协作式取消”，而非程序崩溃故障。
   * C2 吞没异常不再向外传播。执行物理清理（调用 `dispose` 断开与父 Job 的双向链表），将自身 `_state` 指针正式跃迁为最终态 **`Completed`**。
5. **父子回调通知与 C1 状态收敛**：
   * 在 C2 彻底跃迁为 `Completed` 的收尾阶段，底层逻辑会遍历 C2 内部维护的 `NodeList`。遍历命中了在第一阶段建立父子关联时强行插入的 **`ChildHandleNode`** 节点，并执行其 `invoke` 方法。该方法的物理动作是直接利用内部持有的父协程引用，调用 C1 的底层方法，将“子节点已完成”的信号同步给 C1。C1 此时正处于 `Completing`（等待子协程完成）状态。收到通知后，C1 检查发现所有挂载的子节点均已清理完毕，于是 C1 自身也随之跃迁为最终态 **`Completed`**。
6. **EventLoop 终止与主线程释放**：
   * 根协程 C1 (`BlockingCoroutine`) 状态跃迁为 `Completed`，触发了 `runBlocking` 底层事件循环的退出机制。EventLoop 最后一次校验 Ready Queue 与 Delayed Queue，确认内部全空。底层调度器打破 `while(true)` 死循环。物理主线程的调用栈清空，控制权交还操作系统，进程运行结束。

