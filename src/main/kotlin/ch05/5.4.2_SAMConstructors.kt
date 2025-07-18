package ch05.ex4_2_SAMConstructors

// 代码清单 5.14 使用 SAM 构造方法来返回值
fun createAllDoneRunnable(): Runnable {
    return Runnable { println("All done!") }
}

fun main() {
    createAllDoneRunnable().run()
}
/** Output:
 * All done!
 */