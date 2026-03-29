### 协程微观执行流与内存状态流转拆解 (GlobalScope 并发时间差案例)

本解析基于 `job2` 延迟设置为 `900ms` 的代码实例，时间轴精确到毫秒级，追踪 4 个协程状态机在单线程与多线程调度器、3 棵独立 Job 树以及全局延时队列中的物理流转过程。

#### 核心物理实体定义（内存快照）

* **C1 (主协程)**：由 `runBlocking` 创建。运行于单线程 `BlockingEventLoop`。持有 **Root Job 1**。
* **C2 (job1)**：由第一个 `GlobalScope.launch` 创建。运行于多线程单例调度器 `Dispatchers.Default`。持有 **Root Job 2**（无父节点）。
* **C3 (job2)**：由第二个 `GlobalScope.launch` 创建。运行于多线程单例调度器 `Dispatchers.Default`。持有 **Root Job 3**（无父节点）。
* **C4 (Grandchild)**：由 `job1` 内部嵌套的 `launch` 创建。运行于 `Dispatchers.Default`。持有 **Job 4**（父节点指针指向 Root Job 2）。

![CH15_F03_Isakova](https://raw.githubusercontent.com/runningpig66/PicGo/master/20260327183611995.png)

---

#### 第一阶段：启动、上下文断层与并发任务分发 (T = 0 毫秒)

1.  **C1 初始化与启动**
    * 主线程进入 `runBlocking`。底层就地实例化 `BlockingEventLoop`（包含一个基于数组的就绪队列和一个基于时间戳排序的最小堆延时队列）。
    * 创建 Root Job 1，状态初始化为 `Active`。
    * 组装 Context 1 = `EventLoop + Root Job 1`，将其赋值给 C1，并将 C1 压入 EventLoop 的就绪队列。
    * 主线程进入 `while(true)` 事件循环，提取 C1 并调用 `resumeWith` 开始执行。
2.  **C1 孵化 C2 (job1) —— 发生上下文断层 (Context Rupture)**
    * C1 执行第一个 `GlobalScope.launch`。
    * `launch` 尝试从 `GlobalScope` 获取上下文，接收到 `EmptyCoroutineContext`。**C1 的上下文指针链条在此处被强制物理切断**，EventLoop 和 Job 1 均无法向下传递。
    * 底层介入默认配置：由于缺少调度器，指派 `Dispatchers.Default`。该调度器在 JVM 进程中是一个单例（底层实现为 `DefaultScheduler`），维护着全局共享队列和多个 Worker 本地队列。
    * 由于缺少 Job，底层在堆内存中实例化一个全新的 **Root Job 2**（`parent` 指针为 `null`）。
    * 组装 Context 2 = `Dispatchers.Default + Root Job 2`，赋值给 C2。
    * C2 状态机被投递至 `DefaultScheduler` 的并发队列中。
3.  **C1 孵化 C3 (job2) —— 再次断层与共享调度器队列**
    * C1 执行第二个 `GlobalScope.launch`。
    * 发生相同的断层逻辑。实例化 **Root Job 3**。
    * 组装 Context 3 = `Dispatchers.Default + Root Job 3`，赋值给 C3。
    * **队列同一性验证**：C3 被投递至与 C2 完全相同的 `DefaultScheduler` 并发队列中。
4.  **C1 打印与状态机挂起**
    * C1 顺序执行，打印：`0 [main @coroutine#1] Parent done!`。
    * C1 执行 `job1.join()`。底层检查 Root Job 2 的状态，确认为 `Active`。
    * C1 状态机触发挂起流程：将其自身封装为 `AwaitContinuation` 节点，通过 CAS 原语压入 Root Job 2 的监听者链表（Waiters List）。C1 向主线程调用栈返回 `COROUTINE_SUSPENDED`。
    * 主线程的 EventLoop 发现就绪队列为空，调用操作系统原语 `LockSupport.park()`，**主线程陷入物理休眠状态**。
5.  **C2 与 C3 的多线程并发流转**
    * `DefaultScheduler` 的物理 Worker 线程（如 worker-1 和 worker-2）从队列中提取 C2 和 C3。
    * C2 执行遭遇 `delay(1000)`，C3 执行遭遇 `delay(900)`。
    * **延时任务转移**：这两个状态机被封装为 `DelayedResumeTask`，压入底层全局守护线程（`DefaultExecutor`）的时间戳最小堆中进行统一调度。对应的 Worker 线程被释放，转而处理其他任务。

---

#### 第二阶段：C3 (job2) 优先恢复与状态终结 (T = 900 毫秒)

1.  **C3 定时器触发**
    * 现实系统时间来到 900 毫秒。守护线程 `DefaultExecutor` 的定时器中断触发。
    * 将 C3 从最小堆顶剥离，重新投递回 `DefaultScheduler` 的就绪队列。
2.  **C3 执行完毕与内存回收**
    * 闲置的 Worker 线程提取 C3 并恢复其上下文。
    * C3 打印：`929 [DefaultDispatcher-worker-2 @coroutine#3] Child 2 done!`（存在微秒级线程调度延迟）。
    * C3 代码逻辑执行完毕。**Root Job 3 (job2) 的内部状态 `_state` 通过 CAS 操作被强制变更为 `Completed` 这一终态**。C3 状态机进入内存回收流程。

---

#### 第三阶段：C2 (job1) 恢复执行与局部结构化绑定 (T = 1000 毫秒)

1.  **C2 唤醒**
    * 系统时间来到 1000 毫秒。`DefaultExecutor` 触发，将 C2 移回就绪队列，Worker 线程接管并恢复执行。
2.  **C2 孵化 C4 (Grandchild) —— 建立局部防线**
    * C2 执行其代码块内部嵌套的普通 `launch { ... }`。
    * **上下文继承**：由于是标准的 `launch`，它成功读取了 C2 的 Context（`Dispatchers.Default + Root Job 2`）。
    * 底层实例化 **Job 4**。将其 `parent` 指针指向 Root Job 2，并通过 CAS 原语将其作为一个节点插入到 Root Job 2 的 `_children` 无锁双向链表中。
    * 组装 Context 4 = `Dispatchers.Default + Job 4`，赋值给 C4。C4 被压入就绪队列。
3.  **C2 状态流转拦截 (Completing 状态)**
    * C2 打印：`1021 [DefaultDispatcher-worker-2 @coroutine#2] Child 1 done!`。
    * C2 自身的代码指令执行完毕，尝试将 Root Job 2 的状态从 `Active` 变更为 `Completed`。
    * **状态校验拦截**：底层生命周期机制校验 Root Job 2 的 `_children` 链表，发现 Job 4 仍处于 `Active` 状态。
    * 校验未通过，**Root Job 2 的状态转入中间态 `Completing`**（正在完成阶段，等待子节点）。
    * 由于 Root Job 2 尚未达到 `Completed` 终态，注册在 Waiters List 中的 C1 无法被唤醒，主线程继续保持物理休眠。

---

#### 第四阶段：C4 终结与 C1 主协程唤醒 (T = 1250 毫秒)

1.  **C4 延时与调度**
    * Worker 线程提取 C4 执行，遭遇 `delay(250)`。C4 状态机被转移至 `DefaultExecutor` 最小堆，计划唤醒时间戳为 T=1250ms。
2.  **C4 唤醒与状态终结**
    * 时间来到 1250 毫秒。C4 恢复执行，打印：`1273 [DefaultDispatcher-worker-2 @coroutine#4] Grandchild done`。
    * C4 指令执行完毕，Job 4 状态变更为 `Completed`。
3.  **Root Job 2 状态级联变更**
    * Job 4 的完成状态信号顺着 `parent` 指针向上传递至 Root Job 2。
    * Root Job 2 重新校验 `_children` 链表，确认所有挂载的子节点均已处于 `Completed` 状态。
    * Root Job 2 自身状态由 `Completing` 正式跃迁为终态 `Completed`。
4.  **唤醒 C1 (主协程)**
    * 状态变更完成前，Root Job 2 遍历其内部的 Waiters List，提取出被挂起 1250 毫秒的 C1。
    * 调用 `C1.resumeWith(Unit)`。底层系统触发 `LockSupport.unpark()` 操作系统原语，解除主线程的物理休眠。

---

#### 第五阶段：无效的 Cancel 调用与进程安全退出 (T = 1250+ 毫秒)

1.  **C1 恢复流转**
    * 主线程苏醒，`BlockingEventLoop` 恢复事件循环。C1 状态机从 `job1.join()` 的挂起点恢复执行，指令指针推移至下一行。
2.  **执行 `job2.cancel()` 的状态机校验机制**
    * C1 发起方法调用：`job2.cancel()`（即尝试向 Root Job 3 发送取消信号）。
    * **源码级状态校验**：`cancel()` 函数进入底层的首个物理动作，是读取目标 Job 的当前内存状态 `_state`。
    * 依据时间轴推演，Root Job 3 早在 T = 900 毫秒时，已将状态变更为 `Completed`。
    * **生命周期拦截铁律**：若 Job 的状态已处于 `Completed` 或 `Cancelled` 等不可逆终态，传入的 `cancel` 信号将被底层直接拦截并丢弃（Return）。该操作不会抛出任何异常，也不会触发任何内部逻辑回溯。
3.  **主线程终结与进程退出**
    * 取消指令被静默忽略后，C1 所有代码执行完毕。Root Job 1 状态变更为 `Completed`。
    * `runBlocking` 内置的 EventLoop 校验就绪队列与延时最小堆，确认无残留任务，跳出 `while(true)` 死循环。
    * 主线程（作为非守护用户线程）指令执行结束。
    * JVM 监测到进程内已无活跃的非守护线程，立即启动终止序列，安全销毁包括 `DefaultScheduler` 在内的所有后台守护线程，进程正常退出。
