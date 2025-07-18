package ch05.ex1_4_1_AccessingVariablesInScope

// 代码清单 5.10 在 lambda 中使用函数参数
fun printMessagesWithPrefix(messages: Collection<String>, prefix: String) {
    // 在 lambda 中访问 "prefix" 参数
    messages.forEach { println("$prefix $it") }
}

fun main() {
    val errors = listOf("403 Forbidden", "404 Not Found")
    printMessagesWithPrefix(errors, "Error:")
}
/** Output:
 * Error: 403 Forbidden
 * Error: 404 Not Found
 */