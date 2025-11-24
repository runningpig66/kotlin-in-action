package ch04.ex4_4_ObjectExpression2

/**
 * @author runningpig66
 * @date 2025/11/24 周一
 * @time 17:07
 * §4.4 "object" 关键字：将声明一个类与创建一个实例结合起来 §4.4.4 对象表达式：改变写法的匿名内部类
 * P.103 代码清单 4.31 从匿名对象访问局部变量
 *
 * 与 Java 的匿名类一样，在对象表达式中的代码可以访问创建它的函数中的变量。
 * 但是与 Java 不同，访问并没有被限制在 final 变量，还可以在对象表达式中修改变量的值。
 * 例如，我们来看看怎样使用监听器对窗口点击计数。
 */
enum class MouseEvent {
    LEFT_CLICK, RIGHT_CLICK, ROLL_CENTER
}

interface Listener {
    fun mouseClicked(e: MouseEvent)
    fun mouseEntered(e: MouseEvent)
}

abstract class MouseAdapter : Listener {
    abstract override fun mouseClicked(e: MouseEvent)
    abstract override fun mouseEntered(e: MouseEvent)
}

class Window {
    fun addMouseListener(listener: MouseAdapter) {}
}

fun countClicks(window: Window) {
    // 声明局部变量
    var clickCount = 0
    window.addMouseListener(object : MouseAdapter() {
        override fun mouseClicked(e: MouseEvent) {
            // 更新变量的值
            clickCount++
        }

        override fun mouseEntered(e: MouseEvent) {
        }
    })
}

fun endTest(runnable: Runnable) {
    runnable.run()
}

fun main() {
    endTest { println("Any way is okay!") }
}
/* Output:
Any way is okay
 */
