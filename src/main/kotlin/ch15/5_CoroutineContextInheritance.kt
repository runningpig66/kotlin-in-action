package ch15

import kia2e.coroutines.log
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

/**
 * @author runningpig66
 * @date 2026-03-27
 * @time 19:45
 * 15.1.4 Coroutine contexts and structured concurrency
 * 15.1.4 协程上下文与结构化并发
 * {java -Dkotlinx.coroutines.debug ch15._5_CoroutineContextInheritanceKt}
 */
fun main() {
    runBlocking(Dispatchers.Default) {
        log(coroutineContext)
        launch {
            log(coroutineContext)
            launch(Dispatchers.IO + CoroutineName("mine")) {
                log(coroutineContext)
            }
        }
    }
}
/* Output:
0 [DefaultDispatcher-worker-1 @coroutine#1] [CoroutineId(1), "coroutine#1":BlockingCoroutine{Active}@7d0fb299, Dispatchers.Default]
15 [DefaultDispatcher-worker-2 @coroutine#2] [CoroutineId(2), "coroutine#2":StandaloneCoroutine{Active}@55f979aa, Dispatchers.Default]
21 [DefaultDispatcher-worker-1 @mine#3] [CoroutineName(mine), CoroutineId(3), "mine#3":StandaloneCoroutine{Active}@6c7f931c, Dispatchers.IO]
 */
