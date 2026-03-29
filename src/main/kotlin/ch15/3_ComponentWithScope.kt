package ch15.ex2_AssociatingCoroutineScopesWithComponentsCoroutineScope

import kia2e.coroutines.log
import kotlinx.coroutines.*
import kotlin.time.Duration.Companion.milliseconds

/**
 * @author runningpig66
 * @date 2026-03-26
 * @time 4:43
 * Listing 15.2 A component with an associated coroutine scope
 * 代码清单 15.2 具有关联协程作用域的组件
 * {java -Dkotlinx.coroutines.debug ch15.._3_ComponentWithScopeKt}
 */
class ComponentWithScope(dispatcher: CoroutineDispatcher = Dispatchers.Default) {
    private val scope = CoroutineScope(dispatcher + SupervisorJob())
    fun start() {
        log("Starting!")
        scope.launch {
            while (true) {
                delay(500.milliseconds)
                log("Component working!")
            }
        }
        scope.launch {
            log("Doing a one-off task...")
            delay(500.milliseconds)
            log("Task done!")
        }
    }

    fun stop() {
        log("Stopping!")
        scope.cancel()
    }
}

fun main() {
    val c = ComponentWithScope()
    c.start()
    Thread.sleep(2000)
    c.stop()
}
/* Output 1:
0 [main] Starting!
47 [DefaultDispatcher-worker-2 @coroutine#2] Doing a one-off task...
562 [DefaultDispatcher-worker-1 @coroutine#1] Component working!
562 [DefaultDispatcher-worker-2 @coroutine#2] Task done!
1075 [DefaultDispatcher-worker-2 @coroutine#1] Component working!
1588 [DefaultDispatcher-worker-2 @coroutine#1] Component working!
2036 [main] Stopping!
 */
/* Output 2:
0 [main] Starting!
55 [DefaultDispatcher-worker-2 @coroutine#2] Doing a one-off task...
576 [DefaultDispatcher-worker-1 @coroutine#2] Task done!
576 [DefaultDispatcher-worker-2 @coroutine#1] Component working!
1089 [DefaultDispatcher-worker-1 @coroutine#1] Component working!
1604 [DefaultDispatcher-worker-1 @coroutine#1] Component working!
2042 [main] Stopping!
 */
