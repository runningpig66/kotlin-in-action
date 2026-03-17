package ch13.ex1_2_LambdasWithReceivers1

/**
 * @author runningpig66
 * @date 3月14日 周六
 * @time 3:59
 * Listing 13.2 Redefining buildString to take a lambda with a receiver
 * 代码清单 13.2 重新定义 buildString 以接收带有接收者的 lambda
 */
fun buildString(
    builderAction: StringBuilder.() -> Unit // 声明一个带有接收者的函数类型参数
): String {
    val sb = StringBuilder()
    sb.builderAction() // 将 StringBuilder 作为接收者传递给 lambda
    return sb.toString()
}

fun main() {
    val s = buildString {
        this.append("Hello, ") // this 关键字指向 StringBuilder 实例。
        append("World!") // 或者，你可以省略 this 关键字并直接引用 StringBuilder。
    }
    println(s)
}
/* Output:
Hello, World!
 */
