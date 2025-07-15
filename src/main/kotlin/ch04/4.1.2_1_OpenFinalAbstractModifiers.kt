package ch04.ex1_2_1_OpenFinalAbstractModifiers

// 代码清单 4.6 声明一个带一个 open 方法的 open 类
interface Clickable {
    fun click()
    fun showOff() = println("I'm clickable!")
}

open class RichButton : Clickable {
    fun disable() {}
    open fun animate() {}
    override fun click() {}
}