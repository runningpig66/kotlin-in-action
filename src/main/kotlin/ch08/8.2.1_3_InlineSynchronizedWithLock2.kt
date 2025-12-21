package ch08.ex2_1_3_InlineSynchronizedWithLock2

import java.util.concurrent.locks.Lock

/**
 * @author runningpig66
 * @date 2025/12/18 周四
 * @time 23:45
 * §8.2 内联函数：消除 lambda 带来的运行时开销 §8.2.1 内联函数如何运作
 * P.220 代码清单
 *
 * 注意，在调用内联函数的时候也可以传递函数类型的变量作为参数：
 * 在这种情况下，lambda 的代码在内联函数被调用点是不可用的，因此并不会被内联。
 * 只有 synchronized 的函数体被内联了，lambda 才会被正常调用。runUnderLock 函数会被编译成类似于以下函数的字节码：
 *
 * 编译后的字节码：
 * public final class LockOwner {
 *    private final Lock lock;
 *
 *    public LockOwner(Lock lock) {
 *       this.lock = lock;
 *    }
 *
 *    public final Lock getLock() {
 *       return this.lock;
 *    }
 *
 *    public final void runUnderLock(Function0 body) {
 *       lock.lock();
 *       try {
 *          body.invoke(); // body 没有被内联，因为在调用的地方还没有 lambda
 *       } finally {
 *          lock.unlock();
 *       }
 *    }
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

class LockOwner(val lock: Lock) {
    fun runUnderLock(body: () -> Unit) {
        // 传递一个函数类型的变量作为参数，而不是一个 lambda
        synchronized(lock, body)
    }
}
