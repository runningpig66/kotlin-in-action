package ch14

import kia2e.coroutines.log
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlin.coroutines.coroutineContext

/**
 * @author runningpig66
 * @date 2026-03-24
 * @time 22:12
 * 14.8 Coroutines carry additional information in their coroutine context
 * 14.8 协程在其协程上下文中携带附加信息
 * {java -Dkotlinx.coroutines.debug ch14._7_CoroutineContextKt}
 */
suspend fun introspect() {
    log(coroutineContext) // <1> coroutineContext 内置属性包含了协程的上下文信息。
}

fun main() {
    runBlocking {
        introspect()
    }

    runBlocking(Dispatchers.IO + CoroutineName("Coolroutine")) {
        introspect()
    }
}
/* Output:
0 [main @coroutine#1] [CoroutineId(1), "coroutine#1":BlockingCoroutine{Active}@198e2867, BlockingEventLoop@12f40c25]
20 [DefaultDispatcher-worker-1 @Coolroutine#2] [CoroutineName(Coolroutine), CoroutineId(2), "Coolroutine#2":BlockingCoroutine{Active}@278ec4c9, Dispatchers.IO]
 */
