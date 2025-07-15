package ch04.Button1

import java.io.Serializable

// 代码清单 4.9 声明一个包含可序列化状态的视图
interface State : Serializable
interface View {
    fun getCurrentState(): State
    fun restoreState(state: State)
}

// 代码清单 4.11 在 Kotlin 中使用嵌套类来实现 View
class Button : View {
    override fun getCurrentState(): State = ButtonState()
    override fun restoreState(state: State) {}

    // 这个类与 Java 中的静态嵌套类类似
    class ButtonState : State
}