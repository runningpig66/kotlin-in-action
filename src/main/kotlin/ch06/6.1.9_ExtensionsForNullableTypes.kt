package ch06

// 代码清单 6.12 用可空接受者调用扩展函数
fun verifyUserInput(input: String?) {
    if (input.isNullOrBlank()) {
        println("Please fill in the required fields")
    }
}

fun main() {
    verifyUserInput(" ")
    verifyUserInput(null)
}
/** Output:
 * Please fill in the required fields
 * Please fill in the required fields
 */