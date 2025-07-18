package ch05.ex4_2_SAMConstructors2

// 代码清单 5.15 使用 SAM 构造方法来重用 listener 实例
open class View(val id: Int)

@FunctionalInterface
fun interface OnClickListener {
    fun onClick(view: View)
}

class Button(id: Int) : View(id) {
    var listener: OnClickListener? = null
    fun setOnClickListeners(listener: OnClickListener) {
        this.listener = listener
    }
}

fun main() {
    // listener1：使用匿名对象实现接口，适合需要自定义属性或实现多个方法的场景
    val listener1 = object : OnClickListener {
        override fun onClick(view: View) {
            val text = when (view.id) {
                1 -> "First button"
                2 -> "Second button"
                else -> "Unknown button"
            }
            println(text)
        }
    }
    // listener2：使用 SAM 构造函数（lambda），写法更简洁，推荐用于函数式接口（fun interface）
    // 两种写法功能等价，但 lambda 写法性能更优，底层可优化为静态方法或 invokedynamic
    val listener2 = OnClickListener { view ->
        val text = when (view.id) {
            1 -> "First button"
            2 -> "Second button"
            else -> "Unknown button"
        }
        println(text)
    }

    val button1 = Button(1)
    val button2 = Button(2)
    button1.setOnClickListeners(listener1)
    button2.setOnClickListeners(listener2)
}