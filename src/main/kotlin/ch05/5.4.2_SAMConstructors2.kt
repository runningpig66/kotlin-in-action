package ch05.ex4_2_SAMConstructors2

/**
 * @author runningpig66
 * @date 2025/11/29 周六
 * @time 18:01
 * §5.4 使用 Java 函数式接口 §5.4.2 SAM 构造方法：显式地把 lambda 转换成函数式接口
 * P.130 代码清单 5.15 使用 SAM 构造方法来重用 listener 实例
 *
 * 除了返回值外，SAM 构造方法还可以用在需要把从 lambda 生成的函数式接口实例存储在一个变量中的情况。
 * 假设你要在多个按钮上重用同一个监听器，就像下面的代码清单一样：
 * （在 Android 应用中，这段代码可以作为 Activity.onCreate 方法的一部分）。
 *
 * notes: LambdaVsAnonymousObjectListenerRemoval.md
 * 如果你的事件监听器在处理事件时还需要取消它自己，不能使用 lambda 这样做。
 *
 * notes: KotlinSAMConstructorOverloadResolution.md
 * 尽管方法调用中的 SAM 转换一般都自动发生，但是当把 lambda 作为参数传给一个重载方法时，也有编译器不能选择正确的重载的情况。
 */
open class View(val id: Int)

fun interface OnClickListener {
    fun onClick(view: View)
}

class Button(id: Int) : View(id) {
    private var listener: OnClickListener? = null
    fun setOnClickListeners(listener: OnClickListener) {
        this.listener = listener
    }

    fun onClick() {
        listener?.onClick(this)
    }
}

fun main() {
    // listener1: 使用匿名对象实现接口，适合需要自定义属性或实现多个方法的场景
    val listener1 = object : OnClickListener {
        override fun onClick(view: View) {
            val text = when (view.id) {
                1 -> "First button"
                2 -> "Second button"
                else -> "Unknown button"
            }
            println(text) // toast(text)
        }
    }
    // listener2：使用 SAM 构造函数（lambda），写法更简洁，推荐用于函数式接口（fun interface）
    // 两种写法功能等价，但 lambda 写法性能更优，底层可优化为静态方法或 invokedynamic
    val listener2 = OnClickListener { view ->
        // 使用 view.id 来判断点击的是哪个按钮
        val text = when (view.id) {
            1 -> "First button"
            2 -> "Second button"
            else -> "Unknown button"
        }
        println(text) // toast(text)
    }

    val button1 = Button(1)
    val button2 = Button(2)
    button1.setOnClickListeners(listener1)
    button2.setOnClickListeners(listener2)
    button1.onClick()
    button2.onClick()
}
/* Output:
First button
Second button
 */
