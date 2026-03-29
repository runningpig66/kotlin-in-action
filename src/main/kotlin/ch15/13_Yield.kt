package ch15.improved

import kia2e.coroutines.log
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.yield

/**
 * @author runningpig66
 * @date 2026-03-29
 * @time 22:33
 * 15.2.7 Letting other coroutines play: The yield function
 * 15.2.7 让其他协程运行：yield 函数
 * {java -Dkotlinx.coroutines.debug ch15.improved._13_YieldKt}
 */
suspend fun doCpuHeavyWork(): Int {
    log("I'm doing work!")
    var counter = 0
    val startTime = System.currentTimeMillis()
    while (System.currentTimeMillis() < startTime + 500) {
        counter++
        yield()
    }
    return counter
}

fun main() {
    runBlocking {
        launch {
            repeat(3) {
                log(doCpuHeavyWork())
            }
        }
        launch {
            repeat(3) {
                log(doCpuHeavyWork())
            }
        }
    }
}
/* Output 1:
0 [main @coroutine#2] I'm doing work!
10 [main @coroutine#3] I'm doing work!
510 [main @coroutine#3] 141931
510 [main @coroutine#3] I'm doing work!
510 [main @coroutine#2] 141932
510 [main @coroutine#2] I'm doing work!
1010 [main @coroutine#2] 159059
1010 [main @coroutine#2] I'm doing work!
1010 [main @coroutine#3] 159060
1010 [main @coroutine#3] I'm doing work!
1510 [main @coroutine#2] 163483
1510 [main @coroutine#3] 163483
 */
/* Output 2: (注释掉 yield() 调用)
0 [main @coroutine#2] I'm doing work!
510 [main @coroutine#2] 176302947
510 [main @coroutine#2] I'm doing work!
1010 [main @coroutine#2] 178578931
1010 [main @coroutine#2] I'm doing work!
1510 [main @coroutine#2] 177347390
1510 [main @coroutine#3] I'm doing work!
2010 [main @coroutine#3] 192037374
2010 [main @coroutine#3] I'm doing work!
2510 [main @coroutine#3] 190402175
2510 [main @coroutine#3] I'm doing work!
3010 [main @coroutine#3] 188482609
 */
