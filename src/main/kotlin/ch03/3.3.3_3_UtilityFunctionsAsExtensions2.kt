package ch03.ex3_3_3_UtilityFunctionsAsExtensions2

/**
 * @author runningpig66
 * @date 2025/11/19 周三
 * @time 4:09
 * 3.3 给别人的类添加方法：扩展函数和属性：3.3.3 作为扩展函数的工具函数
 * P.56 代码清单
 *
 * 因为扩展函数无非就是静态函数的一个高效的语法糖，可以使用更具体的类型来作为接收者类型，而不是一个类。
 * 假设你想要一个 join 函数，只能由字符串的集合来触发。
 */

fun <T> Collection<T>.joinToString(
    separator: String = ", ",
    prefix: String = "",
    postfix: String = "",
): String {
    val result = StringBuilder(prefix)
    for ((index, element) in this.withIndex()) {
        if (index > 0) {
            result.append(separator)
        }
        result.append(element)
    }
    result.append(postfix)
    return result.toString()
}

fun Collection<String>.join(
    separator: String = ", ",
    prefix: String = "",
    postfix: String = ""
) = this.joinToString(separator, prefix, postfix)

fun main() {
    println(listOf("one", "two", "eight").join(" "))
}
/* Output:
one two eight
 */
