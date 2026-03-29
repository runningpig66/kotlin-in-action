package ch15

import kia2e.coroutines.log
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.job
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * @author runningpig66
 * @date 2026-03-28
 * @time 0:04
 * 15.1.4 Coroutine contexts and structured concurrency
 * 15.1.4 协程上下文与结构化并发
 * {java -Dkotlinx.coroutines.debug ch15._7_AssociatedJobscoroutineScopeKt}
 */
@Suppress("OPT_IN_USAGE")
fun main() = runBlocking<Unit> { // coroutine#1
    log("A's job: ${coroutineContext.job}")
    coroutineScope {
        log("B's parent: ${coroutineContext.job.parent}") // A
        log("B's job: ${coroutineContext.job}") // C
        launch { // coroutine#2
            log("C's parent: ${coroutineContext.job.parent}") // B
        }
    }
}
/* Output:
0 [main @coroutine#1] A's job: "coroutine#1":BlockingCoroutine{Active}@68f7aae2
15 [main @coroutine#1] B's parent: "coroutine#1":BlockingCoroutine{Active}@68f7aae2
15 [main @coroutine#1] B's job: "coroutine#1":ScopeCoroutine{Active}@449b2d27
17 [main @coroutine#2] C's parent: "coroutine#1":ScopeCoroutine{Completing}@449b2d27
 */
