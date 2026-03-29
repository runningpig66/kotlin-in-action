package ch15

import kia2e.coroutines.log
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.time.Duration.Companion.milliseconds

/**
 * @author runningpig66
 * @date 2026-03-28
 * @time 23:05
 * 15.2.1 Triggering cancellation
 * 15.2.1 触发取消
 * {java -Dkotlinx.coroutines.debug ch15._8_CancellationKt}
 */
fun main() {
    runBlocking {
        val launchedJob = launch { // <1> launch returns a Job ...
            log("I'm launched!")
            delay(1000.milliseconds)
            log("I'm done!")
        }
        val asyncDeferred = async { // <2> ... and async returns a Deferred ...
            log("I'm async")
            delay(1000.milliseconds)
            log("I'm done!")
        }
        delay(200.milliseconds)
        launchedJob.cancel() // <3> ... both of which you can cancel.
        asyncDeferred.cancel() // <3>
    }
}
/* Output:
0 [main @coroutine#2] I'm launched!
9 [main @coroutine#3] I'm async
 */
