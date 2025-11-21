package ch04.ex1_3_1_VisibilityModifiers

/**
 * @author runningpig66
 * @date 2025/11/22 周六
 * @time 5:01
 * §4.1 定义类继承结构 §4.1.3 可见性修饰符：默认为 public
 * P.75 代码清单
 *
 * 让我们来看个例子。giveSpeech 函数的每一行都试图违反可见性规则。在编译时就会发生错误。
 * {WillNotCompile}
 */
interface Focusable {
    fun setFocus(b: Boolean) = println("I ${if (b) "got" else "lost"} focus.")
    fun showOff() = println("I'm focusable!")
}

internal open class TalkativeButton : Focusable {
    private fun yell() = println("Hey!")

    // protected 成员只在类和它的子类中可见
    protected open fun whisper() = println("Let's talk!")
}

// 错误："public" 成员暴露了其 "internal" 接收者类型 TalkativeButton
// 同样还要注意的是类的扩展函数不能访问它的 private 和 protected 成员。
fun TalkativeButton.giveSpeech() {
    yell(); // 错误：不能访问 "yell": 它在 TalkativeButton 中是 "private" 的
    whisper(); // 错误：不能访问 "whisper" 它在 "TalkativeButton" 中是 "protected" 的
}
