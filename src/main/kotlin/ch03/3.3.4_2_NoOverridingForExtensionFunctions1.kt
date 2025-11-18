package ch03.ex3_4_2_NoOverridingForExtensionFunctions1

/**
 * @author runningpig66
 * @date 2025/11/19 周三
 * @time 4:28
 * 3.3 给别人的类添加方法：扩展函数和属性：3.3.4 不可重写的扩展函数
 * P.57 代码清单 3.6 不能重写扩展函数
 *
 * 扩展函数并不是类的一部分，它是声明在类之外的。尽管可以给基类和子类都分别定义一个同名的扩展函数，
 * 当这个函数被调用时，它会用到哪一个昵？这里它是由该变量的静态类型所决定的，而不是这个变量的运行时类型。
 *
 * 注意：如果一个类的成员函数和扩展函数有相同的签名，成员函数往往会被优先使用。
 * 你应该牢记，当在扩展 API 类的时候，如果添加一个和扩展函数同名的成员函数，
 * 那么对应类定义的消费者将会重新编译代码，这将会改变它的意义并开始指向新的成员函数。
 */
open class View {
    open fun click() = println("View clicked")
}

class Button : View() {
    override fun click() = println("Button clicked")
}

fun View.showOff() = println("I'm a view!")
fun Button.showOff() = println("I'm a button!")
fun main() {
    val view: View = Button()
    view.showOff() // 扩展函数被静态地解析
    val button: Button = Button()
    button.showOff()
}
/* Output:
I'm a view!
I'm a button!
 */
