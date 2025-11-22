package ch04.Button1

import java.io.Serializable

/**
 * @author runningpig66
 * @date 2025/11/22 周六
 * @time 16:13
 * §4.1 定义类继承结构 §4.1.4 内部类和嵌套类：默认是嵌套类
 * P.77 代码清单 4.9 声明一个包含可序列化状态的视图
 * P.78 代码清单 4.11 在 Kotlin 中使用嵌套类来实现 View
 */
interface State : Serializable
interface View {
    fun getCurrentState(): State
    fun restoreState(state: State) {}
}

class Button : View {
    override fun getCurrentState(): State = ButtonState()
    override fun restoreState(state: State) {
        /*...*/
    }

    // 这个类与 Java 中的 static 嵌套类类似
    class ButtonState : State {
        /*...*/
    }
}
