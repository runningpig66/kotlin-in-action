package ch05.ex5_1_3_TheWithFunction2

/**
 * @author runningpig66
 * @date 2025/11/30 周日
 * @time 15:06
 * §5.5 带接收者的 lambda: "with" 与 "apply" §5.5.1 "with" 函数
 * P.133 代码清单 5.18 使用 with 和一个表达式函数体来构建字母表
 *
 * 让我们进一步重构初始的 alphabet 函数，去掉额外的 stringBuilder 变量。
 *
 * public inline fun <T, R> with(receiver: T, block: T.() -> R): R
 */
fun alphabet() = with(StringBuilder()) {
    for (letter in 'A'..'Z') {
        append(letter)
    }
    append("\nNow I know the alphabet!")
    toString()
}

fun main() {
    println(alphabet())
}
/* Output:
ABCDEFGHIJKLMNOPQRSTUVWXYZ
Now I know the alphabet!
 */
