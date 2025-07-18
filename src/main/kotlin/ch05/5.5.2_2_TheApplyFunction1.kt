package ch05.ex5_2_2_TheApplyFunction1

// 代码清单 5.21 使用 buildString 创建字母表
fun alphabet() = buildString {
    for (letter in 'A'..'Z') {
        append(letter)
    }
    append("\nNow I know the alphabet!")
}

fun main() {
    println(alphabet())
}
/** Output:
 * ABCDEFGHIJKLMNOPQRSTUVWXYZ
 * Now I know the alphabet!
 */