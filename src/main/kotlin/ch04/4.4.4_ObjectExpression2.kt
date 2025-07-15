package ch04.ex4_4_ObjectExpression2

// 代码清单 4.31 从匿名对象访问局部变量
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
/** Output:
 * Any way is okay!
 */