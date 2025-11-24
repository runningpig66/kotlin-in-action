package ch04.ex4_4_ObjectExpression1

/**
 * @author runningpig66
 * @date 2025/11/24 周一
 * @time 16:46
 * §4.4 "object" 关键字：将声明一个类与创建一个实例结合起来 §4.4.4 对象表达式：改变写法的匿名内部类
 * P.102 代码清单 4.30 使用匿名对象来实现事件监听器
 *
 * object 关键字不仅仅能用来声明单例式的对象，还能用来声明匿名对象。匿名对象替代了 Java 中匿名内部类的用法。
 * 除了去掉了对象的名字外，语法是与对象声明相同的。对象表达式声明了一个类并创建了该类的一个实例，
 * 但是并没有给这个类或是实例分配一个名字。通常来说，它们都是不需要名字的，因为你会将这个对象用作一个函数调用的参数。
 * 与 Java 匿名内部类只能扩展一个类或实现一个接口不同，Kotlin 的匿名对象可以实现多个接口或者不实现接口。
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

fun main() {
    val window = Window()
    // 声明一个继承 MouseAdapter 的匿名对象，重写 MouseAdapter 方法
    window.addMouseListener(object : MouseAdapter() {
        override fun mouseClicked(e: MouseEvent) {
        }

        override fun mouseEntered(e: MouseEvent) {
        }
    })

    val window1 = Window()
    val listener = object : MouseAdapter() {
        override fun mouseClicked(e: MouseEvent) {
        }

        override fun mouseEntered(e: MouseEvent) {
        }
    }
    window1.addMouseListener(listener)
}
