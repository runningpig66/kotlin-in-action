package ch04.ex1_2_1_OpenFinalAbstractModifiers

/**
 * @author runningpig66
 * @date 2025/11/22 周六
 * @time 4:00
 * §4.1 定义类继承结构 §4.1.2 open、final 和 abstract 修饰符：默认为 final
 * P.73 代码清单 4.6 声明一个带一个 open 方法的 open 类
 */
interface Clickable {
    fun click()
    fun showOff() = println("I'm clickable!")
}

// 这个类是 open 的：其他类可以继承它
open class RichButton : Clickable {
    // 这个函数是 final 的：不能在子类中重写它
    fun disable() {}

    // 这个函数是 open 的：可以在子类中重写它
    open fun animate() {}

    // 这个函数重写了一个 open 函数并且它本身同样是 open
    override fun click() {}
}
