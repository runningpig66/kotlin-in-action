package ch04.ex1_2_3_AbstractClass

/**
 * @author runningpig66
 * @date 2025/11/22 周六
 * @time 4:47
 * §4.1 定义类继承结构 §4.1.2 open、final 和 abstract 修饰符：默认为 final
 * P.74 代码清单 4.8 声明一个抽象类
 *
 * 在 Kotlin 中，同 Java 一样，可以将一个类声明为 abstract 的，这种类不能被实例化。
 * 一个抽象类通常包含一些没有实现并且必须在子类重写的抽象成员。
 * 抽象成员始终是 open 的，所以不需要显式地使用 open 修饰符。
 */
// 这个类是抽象的：不能创建它的实例
abstract class Animated {
    // 这个函数是抽象的：它没有实现必须被子类重写
    abstract fun animate()

    // 抽象类中的非抽象函数并不是默认 open 的，但是可以标注为 open 的
    open fun stopAnimating() {}
    fun animateTwice() {}
}
