package ch14

import kotlin.concurrent.thread

/**
 * @author runningpig66
 * @date 3月18日 周三
 * @time 17:33
 * 14.3 Comparing threads and coroutines
 * 14.3 比较线程和协程
 */
fun main() {
    println("I'm on ${Thread.currentThread().name}")
    thread { // 启动一个新线程，该线程执行给定的代码块
        println("And I'm on ${Thread.currentThread().name}")
    }
}
/* Output:
I'm on main
And I'm on Thread-0
 */
