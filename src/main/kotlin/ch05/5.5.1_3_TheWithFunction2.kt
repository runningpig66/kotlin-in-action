package ch05.ex5_1_3_TheWithFunction2

// 代码清单 5.18 使用 with 和一个表达式函数体来构建字母表
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
/** Output:
 * ABCDEFGHIJKLMNOPQRSTUVWXYZ
 * Now I know the alphabet!
 */