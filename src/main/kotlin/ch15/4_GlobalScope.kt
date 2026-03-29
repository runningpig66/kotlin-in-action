package ch15

import kia2e.coroutines.log
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.time.Duration.Companion.milliseconds

/**
 * @author runningpig66
 * @date 2026-03-27
 * @time 16:34
 * Listing 15.3 GlobalScope breaks the structured concurrency hierarchy
 * 代码清单 15.3 GlobalScope 破坏了结构化并发层级
 * {java -Dkotlinx.coroutines.debug ch15._4_GlobalScopeKt}
 *
 * notes: 4_GlobalScope.md
 */
@Suppress("OPT_IN_USAGE")
fun main() {
    runBlocking {
        val job1 = GlobalScope.launch { // <1> 在常规应用程序代码中使用 GlobalScope 通常不是一个好主意。
            delay(1000.milliseconds)
            launch {
                delay(250.milliseconds)
                log("Grandchild done")
            }
            log("Child 1 done!")
        }
        val job2 = GlobalScope.launch {
            delay(900.milliseconds)
            log("Child 2 done!")
        }
        log("Parent done!")
        // join() 是一个挂起函数，它的语义是“阻塞当前协程的执行流，直到目标 Job 的状态彻底变成 Completed。
        job1.join()
        // job2.join()
        job2.cancel()
    }
}
/* Output:
0 [main @coroutine#1] Parent done!
935 [DefaultDispatcher-worker-2 @coroutine#3] Child 2 done!
1026 [DefaultDispatcher-worker-2 @coroutine#2] Child 1 done!
1289 [DefaultDispatcher-worker-1 @coroutine#4] Grandchild done
 */
