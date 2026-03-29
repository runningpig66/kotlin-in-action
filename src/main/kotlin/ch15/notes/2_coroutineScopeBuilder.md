### 协程微观执行流与内存状态流转拆解 (coroutineScope 示例)

为了方便推演，我们为代码中出现的四个核心协程分配编号，并基于底层的 `AbstractCoroutine` 机制赋予它们真实的复合物理实体定义：
* **C1**: `runBlocking` 创建的根协程（对应 main 函数体，执行了 `computeSum`）。物理实体为 **`BlockingCoroutine`**。它在内存中同时身兼 Root Job 1 和挂起状态机 Continuation 的双重角色，内部封装并驱动了事件循环（EventLoop）。
* **C2**: `coroutineScope` 创建的作用域协程（对应大括号内的逻辑）。物理实体为 **`ScopeCoroutine`**。同时身兼 Job 2 和 Continuation 的角色，具备接管物理线程执行流的能力。
* **C3**: 第一个 `async` 创建的子协程（计算 a）。物理实体为 **`DeferredCoroutine`**。同时身兼 Job 3 和 Continuation，并额外提供结果存储与 `await` 机制。
* **C4**: 第二个 `async` 创建的子协程（计算 b）。物理实体同样为 **`DeferredCoroutine`**，对应 Job 4。

---

#### 第一阶段：启动引擎与根协程入栈，构建初始上下文

1.  **进入 `runBlocking` 与 EventLoop 初始化**
    * 主线程调用 `runBlocking`。底层引擎检查 `ThreadLocal`，发现当前主线程没有绑定的事件循环，于是就地实例化一个 `BlockingEventLoop`。
    * **上下文元素构建：** 这个 `EventLoop` 在底层实现了 `CoroutineDispatcher` 接口，同时也是一个 `Element`。它内部初始化了两个核心数据结构：Ready Queue（就绪队列，本质是一个基于无锁 CAS 的环形数组，初始为空） 和 Delayed Queue（延时队列，本质是一个基于时间戳排序的最小堆 Min-Heap，初始为空）。

2.  **C1 复合实体与状态机生成、入队**
    * **编译期实体：** 编译器将传给 `runBlocking` 的 Lambda 闭包包装成一个继承自 `SuspendLambda` 的匿名内部类，这是承载真实业务逻辑的状态机实例。
    * **运行期身份合并：** 底层在堆内存中实例化 **`BlockingCoroutine`**（即 C1），初始状态为 `Active`。这避免了分别实例化 Job 和 Continuation 的内存开销。
    * **Context 组装与赋值：** 底层通过 `plus` 操作符，将 EventLoop 和 C1 自身（作为 Root Job 1）组合拼接成一个单向链表结构的 `CoroutineContext` 对象。底层将 C1 的内存指针传入 `SuspendLambda`，并将组装好的链表正式赋值给 `SuspendLambda` 的核心成员变量 `context`。
    * 引擎通过 `C1.context.get(ContinuationInterceptor.Key)` 极速查找到调度器（即刚才的 EventLoop），调用其 `dispatch` 方法，将 C1 状态机压入 EventLoop 的 Ready Queue。
    * 主线程调用 `joinBlocking()`，正式进入一个 `while(true)` 的死循环，开始不断从队列中抽取任务执行。

3.  **C1 开始执行**
    * 主线程从 Ready Queue 取出 C1，调用其 `resumeWith(Unit)`，进而触发内部的 `invokeSuspend`。
    * C1 进入 `computeSum` 函数，打印：`0 [main @coroutine#1] Computing a sum...`
    * C1 的状态机继续往下走，指令执行到了 `coroutineScope` 挂起函数调用的位置。

---

#### 第二阶段：`coroutineScope` 的树状边界构建与执行流接管

`coroutineScope` 是一个极度特殊的挂起函数，它在底层不仅负责挂起，更负责在内存中搭建结构化并发的生命周期边界。

1.  **提取父级 Context 与创建复合实体 C2 (ScopeCoroutine)**
    * 底层获取当前正在执行的协程的上下文，即 `C1.context`。
    * 通过 `C1.context.get(Job.Key)` 提取出父节点 Job 1。
    * **建立父子双向链表绑定：** 底层在堆内存中实例化 **`ScopeCoroutine`**（即 C2，同时作为 Job 2）。在初始化时，底层将其 `parent` 指针指向提取出的外层 Job 1。同时将 Job 2 包装成一个 `ChildHandleNode`，通过 CAS 原语操作，原子性地附加到 Job 1 的内部子节点集合（一个无锁双向链表 `_children`）中，完成结构化并发的挂载。这就构成了内存上的双向绑定：Job 1 的链表里记录了自己有 Job 2 这个孩子，而 Job 2 也持有一个名为 `parent` 的成员变量，指向父节点 Job 1。
2.  **组装新 Context 与执行流接管 (Undispatched)**
    * 底层执行 `C1.context + Job 2 (C2)`。由于 Context 的去重覆盖机制，生成了一个新的单向链表，其中旧的 Job 1 节点被新的 Job 2 节点覆盖，但 EventLoop 调度器被继承保留。将这个全新的 Context 赋值给 `C2.context`。
    * C1 将自身的 `label` 变量更新为下一步的地址，并保存当前的局部变量，然后向调用栈返回 `COROUTINE_SUSPENDED`（主动挂起）。但在单线程调度下，`coroutineScope` 默认采用一种优化机制（Undispatched 直接调用）：主线程的调用栈并没有完全退回到 EventLoop，而是直接接管当前的 CPU 执行流，顺滑地压入了 C2 的 `invokeSuspend` 方法，开始执行作用域内部的代码。
3.  **C2 执行闭包代码，孵化孙子协程 C3 和 C4**
    * 遇到 `val a = async { generateValue() }`。
        * 底层通过 `C2.context.get(Job.Key)` 提取出 Job 2。
        * 实例化 **`DeferredCoroutine`**（即 C3，同时作为 Job 3），并使用相同的 CAS 链表插入逻辑，将 Job 3 (C3) 挂载为 Job 2 (C2) 的 `_children` 子节点。
        * 执行 `C2.context + Job 3` 生成子协程专属 Context 上下文，将新上下文赋值给 `C3.context`。
        * **入队：** 通过 `C3.context.get(ContinuationInterceptor.Key)` 拿到 EventLoop 调度器，将 C3 压入 Ready Queue。
    * 遇到 `val b = async { generateValue() }`。
        * 实例化 **`DeferredCoroutine`**（即 C4，同时作为 Job 4）。使用相同的 CAS 链表插入逻辑，将 Job 4 (C4)  挂载为 Job 2 (C2) 的 `_children` 子节点。
        * 执行 `C2.context + Job 4` 生成专属 Context 并赋值给 `C4.context`。
        * **入队：** 通过 `C4.context.get(ContinuationInterceptor.Key)` 拿到 EventLoop 调度器，将 C4 压入 Ready Queue。
    * 此时 EventLoop 的 Ready Queue 结构为：`[C3, C4]`。
4.  **C2 遭遇 await，主动让出主线程与状态机降级 (SINGLE 优化)**
    * C2 顺序执行到了 `a.await()`。
    * 变量 `a` 即为复合实体 C3（它实现了 `Deferred` 和 `Job` 接口）。底层检查 C3 内部的 `_state` 指针，由于 C3 还在就绪队列排队且尚无任何人等待它，其状态物理指向为一个代表活跃且无监听者的单例对象 **`EmptyActive`**。
    * 既然数据没准备好，C2 必须挂起。C2 将自己的 Continuation 包装成一个 `AwaitContinuation` 回调节点，通过 CAS 操作压入 C3 内部的监听者回调链表（Waiters List）中（实际上触发了 `SINGLE` 优化**没有创建回调链表**）。
    * **极致的内存优化（SINGLE 状态）：** 底层检查 C3 的监听者数量，发现这是 C3 接收的第一个监听者，于是**拒绝在堆内存中分配完整的双向监听者链表（NodeList）**。它直接通过无锁 CAS 操作，将 C3 的 `_state` 指针从 `EmptyActive` 替换并直接指向 C2 封装成的节点实体（即物理上进入 `SINGLE / JobNode` 状态）。
    * 完成注册后，C2 向主线程返回 `COROUTINE_SUSPENDED`。此时，C2 彻底交出 CPU 控制权，方法栈帧弹出，主线程的调用栈清空，控制权弹回到外层 EventLoop 的 `while(true)` 循环中。

---

#### 第三阶段：单线程下的“伪并发”（EventLoop 的精准调度与时间轴计算）

1.  **C3 开始执行并转入延时队列**
    * EventLoop 的死循环发现主线程空闲，从 Ready Queue 头部掏出 C3，调用其 `resumeWith`。
    * C3 开始执行 `generateValue()` 函数，立刻遇到 `delay(500)`。
    * **底层延时机制：** `delay` 首先通过 `C3.context.get(ContinuationInterceptor.Key)` 确认当前协程运行在哪个 EventLoop 上。然后计算出一个绝对的唤醒时间戳（当前时间 + 500ms），将 C3 包装成一个 `DelayedResumeTask` 节点，插入到 EventLoop 的 Delayed Queue（最小堆）中。
    * C3 返回 `COROUTINE_SUSPENDED` 挂起，彻底释放主线程。

2.  **C4 开始执行并转入延时队列**
    * 主线程再次空闲，EventLoop 从 Ready Queue 掏出 C4 执行。
    * C4 同样进入 `generateValue()`，遇到 `delay(500)`。
    * C4 计算唤醒时间戳（比 C3 晚几微秒），被包装并插入 Delayed Queue 最小堆。
    * C4 挂起，再次释放主线程。

3.  **物理线程的深度休眠（Park）**
    * 此时 Ready Queue 完全为空。
    * EventLoop 去窥探 Delayed Queue 的堆顶元素，发现离最近的到期任务（C3）还有近乎 500ms 的时间。
    * 为了不白白空转消耗 CPU，EventLoop 将这个精确的时间差传入系统底层，调用类似于 Java 的 `LockSupport.parkNanos(nanos)` 原语。主线程在操作系统层面被剥夺执行权，陷入深度休眠。

---

#### 第四阶段：唤醒、结果分发与物理指针剥离 (Dispose)

1.  **500ms 后，底层定时器触发**
    * 操作系统层面的定时器中断触发，调用 `unpark` 唤醒主线程。
    * EventLoop 恢复循环，检查 Delayed Queue 堆顶，发现 C3 和 C4 均已到期。
    * EventLoop 将 C3 和 C4 从最小堆中移除，并重新追加到 Ready Queue 中。

2.  **C3 和 C4 计算完成、Dispose 剥离与唤醒 C2**
    * EventLoop 取出 C3 执行。C3 生成随机数（例如 5），将自身 Job 的内部状态 `_state` 从 `Active` 原子修改为 `Completed(5)`。
    * **物理剥离防泄漏：** C3 在生命周期完结前执行核心清理工作，它调用 `parentHandle.dispose()`，主动将自身从父协程（Job 2）的 `_children` 双向链表中物理摘除，斩断强引用以允许 JVM GC 回收。
    * **唤醒单监听者：** C3 检查自身监听者状态，发现内部指针正处于 `SINGLE` 状态（直接指向 C2）。C3 直接调用 `C2.resumeWith(Result.success(5))`，将挂起已久的 C2 重新唤醒并压入 Ready Queue。
    * 随后，EventLoop 取出 C4 执行。C4 生成随机数（例如 3），修改状态为 `Completed(3)`。同样执行 `dispose()` 将自己从 Job 2 的链表剥离。由于 C4 没有监听者等待，直接安静地完成生命周期。

3.  **C2 恢复执行与 Completing 状态收敛**
    * EventLoop 取出 C2，从之前挂起的 `a.await()` 位置恢复上下文，直接拿到传入的参数 `5`。
    * C2 紧接着执行代码 `b.await()`。
    * **极速路径（Fast Path）：** 底层检查 `b` (即 C4) 的 `_state` 字段。发现其已经是 `Completed(3)`。此时，C2 根本不需要执行挂起操作，也不需要注册回调，直接从状态对象中剥离出结果 `3`，继续往下执行。
    * C2 计算出 `5 + 3 = 8`。
    * 此时 C2 的大括号代码块全部执行完毕。**结构化拦截生效：** 底层通过 `C2.context.get(Job.Key)` 拿到 Job 2，将其内部物理对象切换为 `Finishing` 类（对外体现为进入 `Completing` 状态）。它会向下检查自己的双向链表，由于子节点 C3 和 C4 已提前 `dispose` 解绑，链表为空。确认无误后，Job 2 将自身状态正式跃迁为 `Completed`。
    * C2 执行自身的 `parentHandle.dispose()` 从 Job 1 脱离。随后掏出初始化时保存的指向父协程 C1 的引用，调用 `C1.resumeWith(Result.success(8))`。这使得 C1 状态机收到返回值，并被重新压入 Ready Queue 等待调度。

4.  **主协程收尾，进程退出**
    * EventLoop 取出 C1 执行。
    * C1 的状态机从 `coroutineScope` 的挂起点恢复，拿到返回值 8，赋值给 `computeSum` 函数的局部变量 `sum`。
    * C1 继续向下执行 `computeSum` 的后续指令，打印：`547 [main @coroutine#1] Hello~ Hi~`
    * C1 紧接着执行下一行指令，打印：`550 [main @coroutine#1] Sum is 8`。
    * C1 内部全部代码执行完毕。底层通过 `C1.context.get(Job.Key)` 获取到 Root Job 1，使其变更为 `Completing` 状态。它顺着指针检查子链表，确认为空后，将其标记为 `Completed` 终态。
    * `runBlocking` 的 EventLoop 在下一次循环时检查到 Root Job 已彻底完成，且队列中再无残留任务，于是主动打破 `while(true)` 死循环。至此，整个主线程释放，程序正常退出。
