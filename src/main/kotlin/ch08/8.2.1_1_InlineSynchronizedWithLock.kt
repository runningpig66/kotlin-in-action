package ch08.ex2_1_1_InlineSynchronizedWithLock

import java.util.concurrent.locks.Lock
import java.util.concurrent.locks.ReentrantLock

/**
 * @author runningpig66
 * @date 2025/12/18 周四
 * @time 23:08
 * §8.2 内联函数：消除 lambda 带来的运行时开销 §8.2.1 内联函数如何运作
 * P.219 代码清单 8.13 定义一个内联函数
 *
 * 当一个函数被声明为 inline 时，它的函数体是内联的————换句话说，函数体会被直接替换到函数被调用的地方，而不是被正常调用。
 * 代码清单 8.13 中的函数用于确保一个共享资源不会并发地被多个线程访问。函数锁住一个 Lock 对象，执行代码块，然后释放锁。
 */
inline fun <T> synchronized(lock: Lock, action: () -> T): T {
    lock.lock()
    try {
        return action()
    } finally {
        lock.unlock()
    }
}

fun main() {
    val l = ReentrantLock()
    synchronized(l) {
        println("Action")
    }
}
/* Output:
Action
 */
