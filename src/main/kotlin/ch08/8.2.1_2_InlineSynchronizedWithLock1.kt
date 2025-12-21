package ch08.ex2_1_2_InlineSynchronizedWithLock1

import java.util.concurrent.locks.Lock

/**
 * @author runningpig66
 * @date 2025/12/18 周四
 * @time 23:29
 * §8.2 内联函数：消除 lambda 带来的运行时开销 §8.2.1 内联函数如何运作
 * P.220 代码清单
 *
 * 因为已经将 synchronized 函数声明为 inline，所以每次调用它所生成的代码跟 Java 的 synchronized 语句是一样的。
 * 看看下面这个使用 synchronized() 的例子：
 *
 * 注意：编译后的字节码 中 lambda 表达式和 synchronized 函数的实现都被内联了。
 * 由 lambda 生成的字节码成为了函数调用者定义的一部分，而不是被包含在一个实现了函数接口的匿名类中。
 *
 * 编译后的字节码：
 * public static final void foo(Lock l) {
 *    System.out.println("Before sync");
 *    l.lock();
 *    try {
 *       System.out.println("Action"); // 被内联的 lambda 体代码
 *    } finally {
 *       l.unlock();
 *    }
 *    System.out.println("After sync");
 * }
 *
 * 如果在两个不同的位置使用同一个内联函数，但是用的是不同的 lambda，那么内联函数会在每一个被调用的位置被分别内联。
 * 内联函数的代码会被拷贝到使用它的两个不同位置，并把不同的 lambda 替换到其中。
 */
inline fun <T> synchronized(lock: Lock, action: () -> T): T {
    lock.lock()
    try {
        return action()
    } finally {
        lock.unlock()
    }
}

fun foo(l: Lock) {
    println("Before sync")
    synchronized(l) {
        println("Action")
    }
    println("After sync")
}
