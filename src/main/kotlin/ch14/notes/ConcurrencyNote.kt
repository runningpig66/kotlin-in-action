package ch14.notes

import kotlinx.coroutines.*
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlin.time.Duration.Companion.seconds

/**
 * @author runningpig66
 * @date 2026-03-24
 * @time 21:27
 *
 * 在经历了耗时的并发计算（如 heavyImageProcessing）后，面对 x += result 这样极短的共享状态修改操作，
 * 使用 withContext(singleThreadDispatcher) 与使用 Mutex 究竟有哪些优缺点。
 * 我们可以从底层指令流转、上下文切换开销以及 CPU 缓存一致性这三个维度来进行客观的横向对比。
 *
 * 首先分析 withContext(singleThreadDispatcher) 的实现机制与优缺点。
 * 当代码执行到 withContext 时，当前正在执行该协程的底层物理线程（假设为 Dispatchers.Default 池中的线程 A）会将该协程挂起。
 * 底层引擎会将包含 x += result 逻辑的 Continuation 封装成一个任务，并跨线程安全地投递到 singleThreadDispatcher 所维护的内部队列中。
 * 完成投递后，线程 A 立刻被释放，去处理其他的并发任务。而那个专属于 singleThreadDispatcher 的单线程，
 * 则负责不断地从自己的队列中取出任务，并在自己的栈空间内连续执行 x += result。
 *
 * 这种方案的优点在于从物理层面上彻底消灭了并发竞争。因为系统中永远只有一个固定的线程在读取和修改变量 x，
 * 所以不存在任何基于 CAS（比较并交换）的重试逻辑，也不存在协程因为抢不到锁而被迫进入等待队列的反复横跳。
 * 同时，这种单线程独占修改权的模式，极其契合现代 CPU 的缓存架构。由于变量 x 始终被同一个物理核心处理，
 * 它会稳定地驻留在该核心的 L1/L2 缓存中，即便是连续执行一万次累加，
 * 也极少触发缓存失效（Cache Miss）或缓存行跨核同步，内存读写效率达到了单线程情况下的物理极限。
 *
 * 然而，该方案的缺点同样集中在底层的调度开销上。withContext 是一个明确要求切换协程上下文和调度器的操作。
 * 对于 x += result 这样在 CPU 层面上可能只需要两三条机器指令就能完成的动作，为了执行它而付出的前置代价极其高昂。
 * 系统必须为每一次 withContext 调用分配内存来创建任务对象，必须执行跨队列的入队操作，还必须向底层发送信号以确保那个单线程处于活跃状态。
 * 在极其密集的并发涌入时，虽然没有锁竞争，但跨线程投递任务本身的开销，远远超过了执行累加操作本身的开销。
 *
 * 接下来分析 Mutex 方案的实现机制与优缺点。
 * 如果将代码替换为 mutex.withLock { x += result }，协程的执行流并不会主动请求切换线程。
 * 当前负责处理该协程的物理线程（仍假设为线程 A）会直接在原线程尝试获取锁。获取锁的底层动作是一个基于原子指令的 CAS 操作，
 * 它试图将 Mutex 内部的状态标记为已占用。如果此时没有其他线程争抢，CAS 操作瞬间成功，线程 A 直接执行加法并释放锁，随后继续执行后续代码。
 *
 * 这种方案的优点在于其“快速路径（Fast Path）”极其轻量。在低并发或者没有发生碰撞的理想情况下，协程不需要进行任何上下文切换，
 * 也不需要跨队列投递任务。线程 A 可以一路绿灯地执行完所有的逻辑，调度开销几乎为零，这是单线程调度队列无法做到的。
 *
 * 但在这种方案下，缺点会在高并发涌入时被剧烈放大。在你的示例中，一万个协程在经历 10 毫秒的延迟后，
 * 极大概率会在同一时间窗口内苏醒并涌向 mutex.withLock。此时，只有极少数线程的 CAS 操作能成功，绝大多数物理线程会遭遇碰撞。
 * 这些碰撞失败的线程必须执行复杂的挂起流程，将各自的协程打包并送入 Mutex 的等待队列。随后，当锁被释放时，
 * 又依赖于释放锁的线程去唤醒等待队列中的下一个协程，被唤醒的协程又会被重新分配到某个物理线程的局部队列中等待再次执行。
 *
 * 此外，由于 Mutex 允许多个不同的物理线程交替获取锁来修改变量 x，变量 x 所在的内存地址会被不同的 CPU 核心频繁写入。
 * 这在硬件底层会导致严重的缓存一致性流量（MESI 协议同步），迫使各个 CPU 核心的缓存行频繁失效，从而进一步拖慢了原本只需纳秒级完成的加法运算。
 *
 * 总结来看，两者的取舍取决于“临界区代码的执行成本”与“并发碰撞概率”之间的博弈。如果临界区内的数据结构非常复杂，修改操作本身包含一定的逻辑分支或持续时间，
 * 且我们需要绝对避免跨线程修改带来的心智负担和极高的锁竞争损耗，使用单线程调度器（或 Actor 模型）进行队列化隔离是更为稳妥的架构选择。
 *
 * 如果临界区内的操作极度简短，且整体系统的并发碰撞概率较低，使用 Mutex 则能够省去昂贵的上下文切换开销。
 * 当然，在极其极端的场景下，针对单一基础类型（如 Int）的原子性递增，工业界通常会直接使用硬件级别的 AtomicInteger，以彻底规避上述两者的协程级调度损耗。
 */
suspend fun heavyImageProcessing(): Int {
    // 模拟一段耗时且不涉及任何共享变量的 CPU 密集型纯计算代码
    delay(10)
    return 1
}

fun main1() = runBlocking {
    // 创建一个基于 Default 线程池，但并发度严格限制为 1 的专属调度器。
    // 该调度器底层维护一个私有任务队列，确保同一时刻只有一个物理线程执行队列中的任务。
    val singleThreadDispatcher = Dispatchers.Default.limitedParallelism(1)
    var x = 0

    repeat(10_000) {
        // 将一万个任务投递给 Default 调度器的全局队列，由底层多核物理线程并发执行
        launch(Dispatchers.Default) {
            // 阶段一：纯粹的并发计算。多个物理线程在此处全速并行，互不干扰。
            val result = heavyImageProcessing()

            // 阶段二：状态收拢。
            // 缺点：withContext 会触发协程挂起、上下文切换、任务打包以及跨队列投递，
            // 对于极短的操作（如单纯的加法），这部分调度开销远大于操作本身的开销。
            // 优点：彻底规避了基于 CAS 的锁竞争重试机制，消除了多核间的缓存一致性同步损耗。
            withContext(singleThreadDispatcher) {
                x += result
            }
        }
    }

    delay(2.seconds)
    println("Result using limitedParallelism(1): $x")
}

fun main2() = runBlocking {
    val mutex = Mutex()
    var x = 0

    repeat(10_000) {
        // 将一万个任务投递给 Default 调度器的全局队列，由底层多核物理线程并发执行
        launch(Dispatchers.Default) {
            // 阶段一：纯粹的并发计算。
            val result = heavyImageProcessing()

            // 阶段二：使用 Mutex 保护临界区。
            // 优点（低并发情况下的 Fast Path）：如果当前没有其他协程持有该锁，底层的 CAS 操作会瞬间成功，无需挂起当前协程，调度开销极低。
            // 缺点（高并发情况下的 Slow Path）：大量协程同时到达此处时，CAS 会大面积失败。失败的协程将被迫执行复杂的挂起流程，
            // 压入 Mutex 的底层等待队列。随后依赖释放锁的线程进行跨队列唤醒与重定向，导致严重的 CPU 缓存失效与调度损耗。
            mutex.withLock {
                x += result
            }
        }
    }

    delay(2.seconds)
    println("Result using Mutex: $x")
}
