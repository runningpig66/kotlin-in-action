package ch06.ex2_3_3_NumberConversions2

/**
 * @author runningpig66
 * @date 2025/12/2 周二
 * @time 11:49
 * §6.2 基本数据类型和其他基本类型 §6.2.3 数字转换
 * P.162 代码清单
 *
 * Kotlin 标准库提供了一套相似的扩展方法，用来把字符串转换成基本数据类型 (toInt, toByte, toBoolean, and so on):
 * public actual inline fun String.toInt(): Int = java.lang.Integer.parseInt(this)
 */
fun main() {
    println("42".toInt())
}
/* Output:
42
 */
