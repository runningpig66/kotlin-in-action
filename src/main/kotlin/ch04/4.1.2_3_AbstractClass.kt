package ch04.ex1_2_3_AbstractClass

// 代码清单 4.8 声明一个抽象类
abstract class Animated {
    abstract fun animate()

    // 抽象类中的非抽象函数并不是默认 open 的，但是可以标注为 open 的
    open fun stopAnimating() {}
    fun animateTwice() {}
}