package ch15

import kia2e.coroutines.log
import kotlinx.coroutines.*
import kotlin.time.Duration.Companion.milliseconds

/**
 * @author runningpig66
 * @date 2026-03-29
 * @time 3:04
 * 15.2.5 Cancellation is cooperative
 * 15.2.5 取消是协作式的
 * {java -Dkotlinx.coroutines.debug ch15._11_UncancellableCodeKt}
 *
 * notes: 12_UncancellableCode.md
 */
suspend fun doCpuHeavyWork(): Int {
    log("I'm doing work!")
    var counter = 0
    val startTime = System.currentTimeMillis()
    while (System.currentTimeMillis() < startTime + 500) {
        counter++ // <1> 通过递增计数器 500 毫秒来模拟某些 CPU 密集型计算
        // delay(100.milliseconds) // <2> 此函数调用还引入了一个可以取消 doCpuHeavyWork 的点。
    }
    return counter
}

fun main() {
    runBlocking(Dispatchers.Default) {
        val myJob = launch {
            repeat(5) {
                log(doCpuHeavyWork())
                // if (!isActive) return@launch // 检查当前协程作用域是否已被取消
                ensureActive()
            }
        }
        delay(600.milliseconds)
        myJob.cancel()
    }
}
/* Output:
1 [DefaultDispatcher-worker-2 @coroutine#2] I'm doing work!
513 [DefaultDispatcher-worker-2 @coroutine#2] 174475429
513 [DefaultDispatcher-worker-2 @coroutine#2] I'm doing work!
1013 [DefaultDispatcher-worker-2 @coroutine#2] 178743004
 */
