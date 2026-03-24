package ch14

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlin.time.Duration.Companion.seconds

/**
 * @author runningpig66
 * @date 2026-03-24
 * @time 5:16
 *
 * notes: notes/ConcurrencyNote.kt
 */
// Listing 14.9 Running a single coroutine to increment a variable
// 代码清单 14.9 运行单个协程来递增变量
fun main1() {
    runBlocking {
        launch(Dispatchers.Default) { // <1> 在多线程默认调度器上启动单个协程
            var x = 0
            repeat(10_000) {
                x++
            }
            println(x)
        }
    }
}
/* Output:
10000
 */

// Listing 14.10 Starting multiple coroutines to increment a variable
// 代码清单 14.10 启动多个协程来递增变量
fun main2() {
    runBlocking {
        var x = 0
        repeat(10_000) {
            launch(Dispatchers.Default) {
                x++
            }
        }
        delay(1.seconds)
        println(x)
    }
}
/* Output:
9986
 */

// Listing 14.11 Using a Mutex around the critical section
// 列表 14.11 在临界区周围使用 Mutex
fun main3() = runBlocking {
    val mutex = Mutex()
    var x = 0
    repeat(10_000) {
        launch(Dispatchers.Default) {
            mutex.withLock {
                x++
            }
        }
    }
    delay(1.seconds)
    println(x)
}
/* Output:
10000
 */

fun main() = runBlocking {
    // 1. 基于 Default 线程池，派生出一个“最大并行度为 1”的专属调度器
    val singleThreadDispatcher = Dispatchers.Default.limitedParallelism(1)
    var x = 0
    repeat(10_000) {
        // 2. 将协程直接派发给这个单线调度器
        launch(singleThreadDispatcher) {
            x++
        }
    }
    delay(1.seconds)
    println(x)
}
/* Output:
10000
 */
