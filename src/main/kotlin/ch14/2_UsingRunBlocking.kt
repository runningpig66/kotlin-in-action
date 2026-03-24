package ch14

import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import kotlin.time.Duration.Companion.milliseconds

/**
 * @author runningpig66
 * @date 3月18日 周三
 * @time 22:36
 * Listing 14.6 Using runBlocking to invoke a suspending function
 * 代码清单 14.6 使用 runBlocking 调用挂起函数
 *
 * TODO 字节码分析
 */
suspend fun doSomethingSlowly() {
    // suspend fun delay(duration: Duration): Unit
    delay(3000.milliseconds) // 将函数暂停 3000 毫秒。
    println("I'm done")
}

// actual fun <T> runBlocking(context: CoroutineContext, block: suspend CoroutineScope.() -> T): T
fun main() = runBlocking {
    doSomethingSlowly()
    println("blocking after delay")
}
/* Output:
I'm done
blocking after delay
 */
