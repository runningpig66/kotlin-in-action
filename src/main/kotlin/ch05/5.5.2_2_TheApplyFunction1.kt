package ch05.ex5_2_2_TheApplyFunction1

/**
 * @author runningpig66
 * @date 2025/11/30 周日
 * @time 16:06
 * §5.5 带接收者的 lambda: "with" 与 "apply" §5.5.2 "apply" 函数
 * P.133 代码清单 5.21 使用 buildString 创建字母表
 *
 * with 函数和 apply 函数是最基本和最通用的使用带接收者的 lambda 的例子。更多具体的函数也可以使用这种模式。
 * 例如，你可以使用标准库函数 buildString 进一步简化 alphabet 函数，它会负责创建 StringBuilder 并调用 toString.
 * buildString 的实参是一个带接收者的 lambda，接收者就是 StringBuilder.
 * public inline fun buildString(builderAction: StringBuilder.() -> Unit): String
 */
fun alphabet() = buildString {
    for (letter in 'A'..'Z') {
        append(letter)
    }
    append("\nNow I know the alphabet!")
}

fun main() {
    println(alphabet())
}
/* Output:
ABCDEFGHIJKLMNOPQRSTUVWXYZ
Now I know the alphabet!
 */
