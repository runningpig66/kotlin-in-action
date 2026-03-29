package ch15

import kia2e.coroutines.log
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.random.Random
import kotlin.time.Duration.Companion.milliseconds

/**
 * @author runningpig66
 * @date 2026-03-26
 * @time 2:14
 * 15.1.1 Creating a coroutine scope: The coroutineScope function
 * 15.1.1 创建协程作用域：coroutineScope 函数
 * {java -Dkotlinx.coroutines.debug ch15._2_coroutineScopeBuilderKt}
 *
 * 本示例演示了使用 coroutineScope 进行并发任务拆解（Concurrent Decomposition）及其底层的结构化并发机制。
 * 1. 上下文组装与 Job 替换：
 * 执行 coroutineScope 时，它会提取外层协程（此处为 runBlocking）的 CoroutineContext（包含 EventLoop 调度器和外层 Job）。
 * 底层会在内存中实例化一个新的 Job 对象。通过 Context 的组合机制，这个新 Job 会替换掉继承来的上下文中的旧 Job，从而为 coroutineScope 生成一个专属的局部上下文。
 * 2. 建立父子双向链表（挂载机制）：
 * 在初始化新 Job 时，底层会确立父子关系：新 Job 内部的 parent 变量会指向外层 Job；同时，底层通过 CAS 操作，
 * 将新 Job 作为一个节点，插入到外层 Job 内部维护的无锁双向链表（_children 集合）中，完成内存上的物理挂载。
 * 3. 调度方式差异（Undispatched 与 Dispatched）：
 * - coroutineScope 作为挂起函数，执行时会使外层协程主动挂起。它默认在当前执行流中直接运行闭包代码（Undispatched 机制），不会创建新的任务去调度器的就绪队列中排队。
 * - async 是协程构建器。它创建出子协程 Continuation 状态机后，会将其作为新任务投递（Dispatch）到 EventLoop 的就绪队列中，等待物理主线程空闲时提取执行。
 * 4. 结构化并发的等待特性：
 * coroutineScope 对应的 Job 会监听其底层链表上挂载的所有子 Job（即 deferred 任务 a 和 b）。
 * 只有当闭包内的代码执行完毕，且所有子协程的内部状态均变更为 Completed 后，coroutineScope 的局部 Job 才会标记为完成。
 * 此时，它才会唤醒之前被挂起的父协程，交还线程控制权，进而使得代码能继续向下执行并打印 "Hello~ Hi~"。
 *
 * notes: 2_coroutineScopeBuilder.md
 */
suspend fun generateValue(): Int {
    delay(500.milliseconds)
    return Random.nextInt(0, 10)
}

suspend fun computeSum() { // <1> computeSum 是挂起函数
    log("Computing a sum...")
    val sum = coroutineScope { // <2> coroutineScope 函数为你提供了一个作用域
        val a = async { generateValue() }
        val b = async { generateValue() }
        a.await() + b.await() // <3> 在返回之前，coroutineScope 会等待所有子协程执行完毕
    }
    log("Hello~ Hi~")
    log("Sum is $sum")
}

fun main() = runBlocking {
    computeSum()
}
/* Output:
0 [main @coroutine#1] Computing a sum...
547 [main @coroutine#1] Hello~ Hi~
550 [main @coroutine#1] Sum is 8
 */
