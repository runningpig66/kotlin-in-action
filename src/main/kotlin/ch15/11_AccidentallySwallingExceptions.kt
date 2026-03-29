package ch15

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeoutOrNull
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.Duration.Companion.seconds

/**
 * @author runningpig66
 * @date 2026-03-29
 * @time 2:43
 * 15.2.4 Cancelled coroutines throw CancellationExceptions in special places
 * 15.2.4 已取消的协程会在特定位置抛出 CancellationException
 */
suspend fun doWork() {
    delay(500.milliseconds) // <1> 这里抛出的 CancellationException ...
    throw UnsupportedOperationException("Didn't work!")
}

fun main() {
    runBlocking {
        withTimeoutOrNull(2.seconds) {
            while (true) {
                try {
                    doWork()
                }
                /*catch (e: UnsupportedOperationException) {
                    println("Oops: ${e.message}")
                }*/
                catch (e: Exception) { // <2> ... 被吞掉，从而阻止了取消。
                    println("Oops: ${e.message}")
                    // if (e is CancellationException) throw e
                }
            }
        }
    }
}
/* Output:
Oops: Didn't work!
Oops: Didn't work!
Oops: Didn't work!
Oops: Timed out waiting for 2000 ms
Oops: Timed out waiting for 2000 ms
Oops: Timed out waiting for 2000 ms
... (does not terminate)
 */
