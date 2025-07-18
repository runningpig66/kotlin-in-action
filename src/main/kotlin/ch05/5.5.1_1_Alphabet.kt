package ch05.Alphabet

// 代码清单 5.16 构建字母表
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
/**
 * Output:
 * ABCDEFGHIJKLMNOPQRSTUVWXYZ
 * Now I know the alphabet!
 */