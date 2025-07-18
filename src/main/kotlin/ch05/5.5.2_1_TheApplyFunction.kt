package ch05.ex5_2_1_TheApplyFunction

// 代码清单 5.19 使用 apply 构建字母表
fun alphabet() = StringBuilder().apply {
    for (letter in 'A'..'Z') {
        append(letter)
    }
    append("\nNow I know the alphabet!")
}.toString()

fun main() {
    println(alphabet())
}
/** Output:
 * ABCDEFGHIJKLMNOPQRSTUVWXYZ
 * Now I know the alphabet!
 */