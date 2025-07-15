package ch04.ex4_4_ObjectExpression1

// 代码清单 4.30 使用匿名对象来实现事件监听器
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