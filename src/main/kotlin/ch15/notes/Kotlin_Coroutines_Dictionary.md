[TOC]

## Kotlin 协程概念字典

### 1. Continuation（状态机 / 延续体）
* **物理本质**：它是协程指令流转的状态机与核心载体。当你在代码中写下 `suspend fun` 或是传给 `launch` 的尾随 Lambda 时，Kotlin 编译器会在编译期将这些代码块转换成一个继承自 `SuspendLambda`（最终实现了 `Continuation` 接口）的匿名内部类。完整的并发系统物理实体则是由下文的 `AbstractCoroutine` 复合实现的。
* **源码定义（精简版）**：
  ```kotlin
  interface Continuation<in T> {
      val context: CoroutineContext  // 核心成员变量：指向当前协程的上下文环境
      fun resumeWith(result: Result<T>) // 核心方法：推动状态机流转
  }
  ```
* **内部结构**：编译器生成的这个内部类中，包含一个整型的 `label` 字段（记录当前执行到哪一个挂起点），以及多个用于保存局部变量的字段，以确保跨越挂起点时数据不丢失。
* **关联关系**：它是指令执行流的真实载体。它直接持有一个 `CoroutineContext` 成员变量，以获取运行所需的各种底层工具（如调度器、Job 等）。

### 2. CoroutineContext（协程上下文环境）
* **物理本质**：它在宏观使用上类似于 Map，但**在底层真实的物理内存中，它根本没有任何哈希表结构，而是一个左偏向的单向链表（CombinedContext）**。设计极其精巧。
* **源码定义（精简版）**：
  ```kotlin
  interface CoroutineContext {
      operator fun <E : Element> get(key: Key<E>): E? // 泛型推导获取元素
  }
  ```
* **核心组成（Element 与 Key 的魔法）**：
  * **Element（工具元素）**：这个集合中存放的数据必须是 `Element`。协程体系中的核心组件（包括 `Job`、`CoroutineDispatcher` 等）都实现了这个接口。`Element` 本身也继承自 `CoroutineContext`，意味着一个单独的工具本身就是一个容量为 1 的上下文节点。
  * **Key（类型令牌）**：每个 `Element` 必须提供一个全局唯一的静态单例（通常是伴生对象 `Companion object`）作为 `Key`。底层通过严格的 `===`（内存地址比对）进行 O(1) 的极速查找，并通过泛型 `<E>` 实现绝对的类型安全（免强转）。
* **关联关系**：
  * **被谁持有**：被 `Continuation` 和 `CoroutineScope` 持有。
  * **持有谁**：它是 `Job` 和 `CoroutineDispatcher` 等实例的真正物理容器，通过 `plus` 操作符（`+`）拼接链表节点。

### 3. Job（生命周期与结构树节点）
* **物理本质**：它是一个纯粹的数据结构，不包含任何业务执行代码。它的底层核心实现类是 `JobSupport`。
* **存在位置**：`Job` 实例存放在当前协程的 `CoroutineContext` 链表中。当需要获取当前协程的 Job 时，底层代码会调用 `continuation.context[Job]` 取出对应的实例。
* **内部结构**：
  * `_state`：一个原子引用，记录当前状态（如 `Active`、`Completing`、`Cancelled`）。
  * `parent` / `_parentHandle`：一个指针，指向创建它的父 Job（确立从属关系）。
  * `_children`：一个基于 CAS 的无锁双向链表，记录所有由它衍生的子 Job 节点。
* **关联关系**：它是实现“结构化并发”的唯一内存依据，负责向下传递取消信号（Cancellation），向上传递完成状态（Completion）和异常。

### 4. CoroutineDispatcher（协程调度器 / 执行路由）
* **物理本质**：它决定了 `Continuation` 的 `resumeWith` 方法（即状态机的下一步指令）应该被提交到哪一个操作系统的物理线程去执行。
* **存在位置**：它同样作为一个 `Element`，存放在当前协程的 `CoroutineContext` 链表中。
* **核心实现分类**：
  * **单线程事件循环（`EventLoop` / `runBlocking` 默认调度器）**：
    * **数据结构**：内含一个普通的无锁双向队列（Ready Queue）和一个基于时间戳排序的最小堆（Delayed Queue）。
    * **流转机制**：将传入的 `Continuation` 压入本地队列，主线程通过 `while(true)` 死循环不断从队列头部取出 `Continuation` 执行。若队列为空则调用 `LockSupport.park()` 让物理线程休眠。
  * **多线程窃取池（`CoroutineScheduler` / `Dispatchers.Default`）**：
    * **数据结构**：内含一个跨线程共享的全局队列（Global Queue），以及为每一个底层 OS 物理线程（Worker）分配的固定大小的私有局部队列（Local Queue）。
    * **流转机制**：将传入的 `Continuation` 压入全局队列或当前 Worker 的局部队列。Worker 优先处理私有局部队列，空闲时通过 Work-Stealing（工作窃取）算法去其他 Worker 的局部队列尾部窃取 `Continuation`，以此保证多核 CPU 满载运转且无锁竞争。

### 5. CoroutineScope（协程作用域 / 静态边界）
* **物理本质**：它是整个体系中最简单的一个接口。它不是执行流，也不是状态机。
* **源码定义（完整版）**：
  ```kotlin
  public interface CoroutineScope {
      public val coroutineContext: CoroutineContext
  }
  ```
* **存在意义与关联关系**：
  * 它仅仅持有一个 `CoroutineContext` 引用。
  * 它存在的唯一目的是为了语法层面的限制和规范。构建协程的函数（如 `launch` 和 `async`）被设计为 `CoroutineScope` 的扩展函数：`fun CoroutineScope.launch(...)`。
  * 这强制要求你在调用 `launch` 时，必须提供一个 `CoroutineScope`。编译器会提取该 Scope 中的 `Context`（进而拿到里面的 `Job` 和 `Dispatcher`），并将这些数据混入到新创建的 `Continuation` 中，从而在内存中建立父子 `Job` 链接，完成结构化并发的闭环。

### 6. AbstractCoroutine（协程底层基类 / 复合物理实体）
* **物理本质**：这是 Kotlin 协程框架在堆内存中真实实例化的**复合物理实体**。为了极致的内存分配优化与降低 GC（垃圾回收）压力，该抽象类在底层源码中统一实现了三大核心接口：负责状态机流转的 `Continuation`、负责结构化并发与生命周期的 `Job`，以及负责提供局部边界外壳的 `CoroutineScope`。
* **架构意义**：通过单一对象的多态特性，彻底避免了每次启动协程时需分别实例化三个独立对象（即状态机 `Continuation`、生命周期节点 `Job`、作用域外壳 `CoroutineScope`）所带来的堆内存开销。在底层物理运转时：
  * 当进入调度器队列排队或恢复执行时，底层将其向上转型为 `Continuation` 对待。
  * 当处理异常拦截、维护父子双向链表或传递取消信号时，底层将其作为 `Job` 对待。
  * 当为大括号闭包（Lambda）提供 `this` 上下文指针时，底层将其作为 `CoroutineScope` 暴露给开发者。
* **核心派生实现类**（依据不同的协程构建器产生不同的物理实体）：
  * **`BlockingCoroutine`**：
    * **生成者**：由 `runBlocking` 函数实例化。
    * **特性**：其内部封装了 `EventLoop` 机制，赋予该实体阻塞其所依附的物理主线程（使其陷入 Park 状态）的能力，直至内部所有状态机流转完毕。
  * **`StandaloneCoroutine`**：
    * **生成者**：由标准的 `launch` 函数实例化。
    * **特性**：代表一个标准的后台并发任务实体，不提供任何向外返回业务执行结果的方法，仅向上层汇报 `Completed` 或 `Cancelled` 的生命周期终态。
  * **`DeferredCoroutine`**：
    * **生成者**：由 `async` 函数实例化。
    * **特性**：除了具备标准协程特性外，额外实现了 `Deferred` 接口，内部提供用于存储和获取泛型结果（或捕获异常）的同步阻塞与挂起机制（即 `await()` 方法）。
  * **`ScopeCoroutine`**：
    * **生成者**：由 `coroutineScope { ... }` 等作用域挂起函数实例化。
    * **特性**：它不进入调度器的就绪队列（Undispatched 机制），而是直接接管当前执行流。其核心职责是建立局部的结构化并发边界，拦截子协程的异常向上传递，并强制父协程挂起等待其底层链表上的子节点全部完成。

---

### 流程映射小结

#### 场景 A：当调用 `launch` 时发生了什么？（Dispatched 派发机制）
当我们把上述概念连接起来，一次 `launch` 调用的宏观与微观边界如下：
1. **起始点**：你在某个 `CoroutineScope` 对象上调用了 `launch { ... }`。
2. **提取环境**：`launch` 函数读取该 Scope 内部的 `CoroutineContext`，提取出 **父 Job** 和 **CoroutineDispatcher**。
3. **生成载体**：编译器将大括号内的代码编译生成一个 **Continuation** 状态机实例。
4. **构建关联（上下文替换与挂载）**：底层新建一个 **子 Job**（物理实体为 `StandaloneCoroutine`），将其内部指针指向父 Job，并通过 CAS 插入父 Job 的 `_children` 双向链表。将这个新子 Job 与提取出的旧 Context 进行 `plus` 组合（覆盖掉旧 Job），赋值给新建的 Continuation 的 `context` 成员变量。
5. **提交路由**：底层调用提取出的 CoroutineDispatcher 的 `dispatch` 方法，将这个准备就绪的 Continuation **压入对应线程池的就绪队列**中，等待物理线程提取执行。当前父协程继续无阻塞向下执行。

#### 场景 B：当调用 `coroutineScope { ... }` 时发生了什么？（Undispatched 挂起机制）
1. **生成载体与挂载**：同样生成新的 Continuation 和子 Job（物理实体为 `ScopeCoroutine`），并挂载到父 Job 的链表下，组装专属 Context。
2. **接管与挂起**：它不会将任务压入就绪队列。它会直接接管当前物理线程的执行权，开始执行闭包内的代码，同时向调用栈返回 `COROUTINE_SUSPENDED`，迫使父协程原地挂起。
3. **强制收敛**：它会持续监听挂载于自身节点下的所有子 Job。只有当闭包内代码执行完毕，且底层双向链表上的所有子节点均流转至 `Completed` 状态时，它才会唤醒父协程恢复执行。**这正是结构化并发确保生命周期严格同步、防止后台协程泄漏的核心物理机制。**
