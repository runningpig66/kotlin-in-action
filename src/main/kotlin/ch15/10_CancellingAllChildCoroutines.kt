package ch15

import kia2e.coroutines.log
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.time.Duration.Companion.milliseconds

/**
 * @author runningpig66
 * @date 2026-03-29
 * @time 1:21
 * Listing 15.4 Cancelling all child coroutines automatically
 * 代码清单 15.4 自动取消所有子协程
 * {java -Dkotlinx.coroutines.debug ch15._10_CancellingAllChildCoroutinesKt}
 */
fun main() = runBlocking {
    val job = launch {
        launch {
            launch {
                launch { // <1> 该协程是已被取消的作业（job）的曾孙协程。
                    log("I'm started")
                    delay(500.milliseconds)
                    log("I'm done!")
                }
            }
        }
    }
    delay(200.milliseconds)
    job.cancel()
}
/* Output:
0 [main @coroutine#5] I'm started
 */
