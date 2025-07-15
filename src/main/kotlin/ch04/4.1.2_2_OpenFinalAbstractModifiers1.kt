package ch04.ex1_2_2_OpenFinalAbstractModifiers1

// 代码清单 4.7 禁止重写
interface Clickable {
    fun click()
    fun showOff() = println("I'm clickable!")
}

open class RichButton : Clickable {
    final override fun click() {}
}