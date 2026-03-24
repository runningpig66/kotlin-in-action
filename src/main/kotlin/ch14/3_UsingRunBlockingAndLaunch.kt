package ch14

import kia2e.coroutines.log
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.time.Duration.Companion.milliseconds

/**
 * @author runningpig66
 * @date 3月18日 周三
 * @time 23:05
 * 14.6.2 Creating start-and-forget coroutines: The launch function
 * 14.6.2 创建“启动即忘记”协程：launch 函数
 * {java -Dkotlinx.coroutines.debug ch14._3_UsingRunBlockingAndLaunchKt}
 */
fun main() = runBlocking {
    log("The first, parent, coroutine starts")
    launch {
        log("The second coroutine starts and is ready to be suspended")
        delay(3000.milliseconds)
        log("The second coroutine is resumed")
    }
    launch {
        log("The third coroutine can run in the meantime")
    }
    log("The first coroutine has launched two more coroutines")
}
/* Output: (-Dkotlinx.coroutines.debug)
0 [main @coroutine#1] The first, parent, coroutine starts
16 [main @coroutine#1] The first coroutine has launched two more coroutines
19 [main @coroutine#2] The second coroutine starts and is ready to be suspended
28 [main @coroutine#3] The third coroutine can run in the meantime
3028 [main @coroutine#2] The second coroutine is resumed
 */
