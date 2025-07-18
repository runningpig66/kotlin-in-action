package ch05.ex5_1_2_TheWithFunction1

// 代码清单 5.17 使用 with 构建字母表
fun alphabet(): String {
    val stringBuilder = StringBuilder()
    return with(stringBuilder) {
        for (letter in 'A'..'Z') {
            this.append(letter)
        }
        append("\nNow I know the alphabet!")
        this.toString()
    }
}

fun main() {
    println(alphabet())
}
/** Output:
 * ABCDEFGHIJKLMNOPQRSTUVWXYZ
 * Now I know the alphabet!
 */