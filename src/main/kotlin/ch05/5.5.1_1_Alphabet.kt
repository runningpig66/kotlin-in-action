package ch05.Alphabet

/**
 * @author runningpig66
 * @date 2025/11/30 周日
 * @time 14:43
 * §5.5 带接收者的 lambda: "with" 与 "apply" §5.5.1 "with" 函数
 * P.131 代码清单 5.16 构建字母表
 */
fun alphabet(): String {
    val result = StringBuilder()
    for (letter in 'A'..'Z') {
        result.append(letter)
    }
    result.append("\nNow I know the alphabet!")
    return result.toString()
}

fun main() {
    println(alphabet())
}
/* Output:
ABCDEFGHIJKLMNOPQRSTUVWXYZ
Now I know the alphabet!
 */
