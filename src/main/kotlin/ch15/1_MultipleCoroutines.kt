package ch15

import kia2e.coroutines.log
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.Duration.Companion.seconds

/**
 * @author runningpig66
 * @date 2026-03-26
 * @time 0:08
 * Listing 15.1 Starting a number of different coroutines
 * 清单 15.1 启动多个不同的协程
 * {java -Dkotlinx.coroutines.debug ch15._1_MultipleCoroutinesKt}
 */
fun main() {
    runBlocking { // this: CoroutineScope <1> 隐式接收者
        launch { // this: CoroutineScope <2> 由 launch 启动的协程是父级 runBlocking 协程的子协程
            delay(1.seconds)
            launch {
                delay(250.milliseconds)
                log("Grandchild done")
            }
            log("Child 1 done!")
        }
        launch {
            delay(500.milliseconds)
            log("Child 2 done!")
        }
        log("Parent done!")
    }
}
/* Output:
0 [main @coroutine#1] Parent done!
525 [main @coroutine#3] Child 2 done!
1024 [main @coroutine#2] Child 1 done!
1286 [main @coroutine#4] Grandchild done
 */
