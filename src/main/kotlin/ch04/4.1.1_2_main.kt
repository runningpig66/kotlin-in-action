package ch04.main

// 代码清单 4.3 在接口中定义一个带方法体的方法
interface Clickable {
    fun click()

    // 带默认实现的方法
    fun showOff() = println("I'm clickable!")
}

// 代码清单 4.4 定义另一个实现了同样方法的接口
interface Focusable {
    fun setFocus(b: Boolean) = println("I ${if (b) "got" else "lost"} focus.")
    fun showOff() = println("I'm focusable!")
}

// 代码清单 4.5 调用继承自接口方法的实现
class Button : Clickable, Focusable {
    override fun click() = println("I was clicked.")
    override fun showOff() {
        // 如果同样的继承成员有不止一个实现，必须提供一个显式实现
        super<Clickable>.showOff()
        super<Focusable>.showOff()
    }
}

fun main() {
    val button = Button()
    button.showOff()
    button.setFocus(true)
    button.click()
}
/** Output:
 * I'm clickable!
 * I'm focusable!
 * I got focus.
 * I was clicked.
 */