package ch05.ex1_4_2_AccessingVariablesInScope2

/**
 * @author runningpig66
 * @date 2025/11/25 周二
 * @time 22:32
 * §5.1 Lambda 表达式和成员引用 §5.1.4 在作用域中访问变量
 * P.113 代码清单
 *
 * 这里有一个重要的注意事项，如果 lambda 被用作事件处理器或者用在其他异步执行的情况，
 * 对局部变量的修改只会在 lambda 执行的时候发生。例如，下面这段代码并不是记录按钮点击次数的正确方法：
 * 补充说明：书中这个例子想表达的是：lambda 里确实改到了外层的变量（闭包捕获的是同一个 clicks），
 * 但 onClick 回调是“将来”才执行的，而你在函数里是“现在就 return 了”，所以返回的永远是注册监听时的值（0）。
 * 但对于经验丰富的开发人员来说，onClick = 以后才会被调用，是肌肉记忆；所以你可能从一开始就
 * 不会期待“return clicks 能看到点击后的结果”；反而会被这个例子带偏，怀疑是不是在讲什么更深的黑魔法。
 * 但这段代码在书里的使命真的就只有一句话：
 * “闭包可以改局部变量，但如果是在事件/异步里改，你在函数返回时是看不到改后的结果的。”
 *
 * notes: AccessingVariablesInScope2.md & 5.1.4_2_AccessingVariablesInScope2.kt
 * 我最后想确认的是，在这个代码中，我们实际上确实修改了 clicks 的值，从 print 语句就可以看出来。
 * 只不过我们过早地返回了该值（值传递），然后我们又没有这个值的引用，
 * 虽然实际上在点击的时候确实修改了这个值，但是我们却没有这个值的引用去访问了，对吧。
 */
// 模拟简单的 View 标记接口
interface View

// 仿 Android 的 OnClickListener，写成 fun interface 方便用 lambda
fun interface OnClickListener {
    fun onClick(view: View)
}

// 仿 Android 的 Button：内部持有一个 OnClickListener
class Button : View {
    private var listener: OnClickListener? = null

    fun setOnClickListener(listener: OnClickListener?) {
        this.listener = listener
    }

    fun onClick() {
        println("Button clicked!")
        listener?.onClick(this)
    }
}

// 书中的错误示例：试图统计按钮点击次数，但这段代码总是返回 0。
fun tryToCountButtonClicks(button: Button): Int {
    var clicks = 0
    // 这里注册了一个监听器：将来按钮被点击时执行 clicks++
    button.setOnClickListener { view ->
        clicks++
        println(clicks) // 让我们打印看看~
    }
    // 注意：此时按钮根本还没有被点击，clicks 仍然是 0
    return clicks
}

fun makeCounter(): () -> Int {
    var counter = 0
    return { ++counter }
}

fun main() {
    val button = Button()

    // 调用示例函数：此时只是“注册监听器”，没有发生任何点击
    var result = tryToCountButtonClicks(button)
    println("tryToCountButtonClicks返回值：$result")   // 始终是 0

    println("—— 函数已经返回了，现在再来点击按钮 ——")
    button.onClick()
    button.onClick()
    button.onClick()

    println("tryToCountButtonClicks返回值：$result")   // 始终是 0

    val makeCounter: () -> Int = makeCounter()
    println(makeCounter())
    println(makeCounter())
    println(makeCounter())

}
/* Output:
tryToCountButtonClicks返回值：0
—— 函数已经返回了，现在再来点击按钮 ——
Button clicked!
1
Button clicked!
2
Button clicked!
3
tryToCountButtonClicks返回值：0
1
2
3
 */
