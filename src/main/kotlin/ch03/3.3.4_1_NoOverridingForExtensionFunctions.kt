package ch03.ex3_4_1_NoOverridingForExtensionFunctions

/**
 * @author runningpig66
 * @date 2025/11/19 周三
 * @time 4:21
 * §3.3 给别人的类添加方法：扩展函数和属性 §3.3.4 不可重写的扩展函数
 * P.56 代码清单 3.5 重写成员函数
 *
 * 在 Kotlin 中，重写成员函数是很平常的一件事情。但是，不能重写扩展函数。
 */

open class View {
    open fun click() = println("View clicked")
}

class Button : View() { // Button 继承 View
    override fun click() = println("Button clicked")
}

fun main() {
    val view: View = Button()
    view.click() // 具体调用哪个方法，由实际的 view 的值来决定
}
/* Output:
Button clicked
 */
