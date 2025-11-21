package ch04.main

/**
 * @author runningpig66
 * @date 2025/11/22 周六
 * @time 3:16
 * §4.1 定义类继承结构 §4.1.1 Kotlin 中的接口
 * P.71 代码清单 4.3 在接口中定义一个带方法体的方法
 * P.71 代码清单 4.4 定义另一个实现了同样方法的接口
 * P.71 代码清单 4.5 调用继承自接口方法的实现
 */
// 接口的方法可以有一个默认实现。与 Java8 不同的是，Java8 中需要你在这样的实现上标注 default 关键字，
// 对于这样的方法 Kotlin 没有特殊的注解，只需要提供一个方法体。
// 让我们来给 Clickable 接口添加一个带默认实现的方法。
interface Clickable {
    // 如果你实现了这个接口，你需要为 click 提供一个实现。
    fun click() // 普通的方法声明

    // 可以重新定义 showOff 方法的行为，或者如果你对默认行为感到满意也可以直接省略它。
    fun showOff() = println("I'm clickable!") // 带默认实现的方法
}

// 现在让我们假设存在同样定义了一个 showOff 方法并且有如下实现的另一个接口。
interface Focusable {
    fun setFocus(b: Boolean) = println("I ${if (b) "got" else "lost"} focus.")
    fun showOff() = println("I'm focusable!")
}

class Button : Clickable, Focusable {
    override fun click() = println("I was clicked.")

    // 如果同样的继承成员有不止一个实现，必须提供一个显式实现
    override fun showOff() {
        // 使用尖括号 <> 加上父类型名字的 "super" 表明了你想要调用哪一个父类的方法
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
/* Output:
I'm clickable!
I'm focusable!
I got focus.
I was clicked.
 */
