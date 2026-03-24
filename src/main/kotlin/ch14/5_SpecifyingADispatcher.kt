package ch14

import kia2e.coroutines.log
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

/**
 * @author runningpig66
 * @date 2026-03-24
 * @time 4:48
 * Listing 14.8 Specifying a dispatcher as a parameter to a coroutine builder
 * 代码清单 14.8 将调度器作为参数传递给协程构建器
 * {java -Dkotlinx.coroutines.debug ch14.ch14._5_SpecifyingADispatcherKt}
 */
fun main() {
    runBlocking {
        log("Doing some work")
        launch(Dispatchers.IO) { // <1> 将协程的调度器设置为 Dispatchers.IO
            log("Doing some background work")
            withContext(Dispatchers.Default) {
                log("Doing some work")
            }
        }
    }
}
/* Output:
0 [main @coroutine#1] Doing some work
16 [DefaultDispatcher-worker-1 @coroutine#2] Doing some background work
 */
