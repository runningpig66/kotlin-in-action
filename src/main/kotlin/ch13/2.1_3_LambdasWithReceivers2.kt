package ch13.ex1_3_LambdasWithReceivers2

/**
 * @author runningpig66
 * @date 3月14日 周六
 * @time 5:06
 * Listing 13.3 Storing a lambda with a receiver in a variable
 * 代码清单 13.3 将带接收者的 lambda 存储在变量中
 */
val appendExcl: StringBuilder.() -> Unit = // appendExcl 是一个扩展函数类型的值。
    { this.append("!") }

fun main() {
    val stringBuilder = StringBuilder("Hi")
    stringBuilder.appendExcl() // 你可以将 appendExcl 作为扩展函数进行调用。
    println(stringBuilder)
    // inline fun buildString(builderAction: StringBuilder.() -> Unit): String
    println(buildString(appendExcl)) // 你也可以将 appendExcl 作为参数传递。
}
/* Output:
Hi!
!
 */
