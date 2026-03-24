package ch14

import kia2e.coroutines.log
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.time.Duration.Companion.milliseconds

/**
 * @author runningpig66
 * @date 2026-03-24
 * @time 0:11
 * Listing 14.7 Using the async coroutine builder to start a new coroutine
 * 代码清单 14.7 使用 async 协程构建器启动新协程
 * {java -Dkotlinx.coroutines.debug ch14._4_UsingAsyncKt}
 */
suspend fun slowlyAddNumbers(a: Int, b: Int): Int {
    log("Waiting a bit before calculating $a + $b")
    delay(3000.milliseconds * a)
    return a + b
}

fun main() = runBlocking {
    log("Starting the async computation")
    val myFirstDeferred = async { slowlyAddNumbers(2, 2) } // <1> 为每个 async 调用启动一个新协程
    val mySecondDeferred = async { slowlyAddNumbers(4, 4) } // <1>
    log("Waiting for the deferred value to be available")
    log("The first result: ${myFirstDeferred.await()}") // <2> 等待结果可用
    log("The second result: ${mySecondDeferred.await()}") // <2>
}
/* Output:
0 [main @coroutine#1] Starting the async computation
18 [main @coroutine#1] Waiting for the deferred value to be available
31 [main @coroutine#2] Waiting a bit before calculating 2 + 2
38 [main @coroutine#3] Waiting a bit before calculating 4 + 4
6049 [main @coroutine#1] The first result: 4
12044 [main @coroutine#1] The second result: 8
 */
