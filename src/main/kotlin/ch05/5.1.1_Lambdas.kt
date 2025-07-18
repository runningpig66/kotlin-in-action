package ch05.ex1_1_Lambdas

// 代码清单 5.2 用 lambda 实现监听器
@FunctionalInterface
fun interface OnClickListener {
    fun onClick()
}

class Button {
    fun setOnClickListener(listener: OnClickListener?) {
    }
}

fun main() {
    val button = Button()
    button.setOnClickListener { /* 点击后执行的动作 */ }
}