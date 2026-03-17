package ch13.ex1_1_LambdasWithReceivers

/**
 * @author runningpig66
 * @date 3月14日 周六
 * @time 3:46
 * Listing 13.1 Defining buildString() that takes a lambda as an argument
 * 代码清单 13.1 定义以 lambda 作为参数的 buildString()
 */
fun buildString(
    builderAction: (StringBuilder) -> Unit // 声明一个函数类型的参数
): String {
    val sb = StringBuilder()
    builderAction(sb) // 将 StringBuilder 作为参数传递给 lambda
    return sb.toString()
}

fun main() {
    val s = buildString {
        it.append("Hello, ") // 使用 it 函数来引用 StringBuilder 实例
        it.append("World!")
    }
    println(s)
}
/* Output:
Hello, World!
 */
