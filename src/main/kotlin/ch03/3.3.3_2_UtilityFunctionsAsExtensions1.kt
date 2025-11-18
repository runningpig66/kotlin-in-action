package ch03.ex3_3_2_UtilityFunctionsAsExtensions1

/**
 * @author runningpig66
 * @date 2025/11/19 周三
 * @time 4:02
 * 3.3 给别人的类添加方法：扩展函数和属性：3.3.3 作为扩展函数的工具函数
 * P.55 代码清单
 *
 * 可以给元素的集合类添加一个扩展函数，然后给所有的参数添加一个默认值。
 * 这样，就可以像使用一个类的成员函数一样，去调用 joinToString 了：
 */
fun <T> Collection<T>.joinToString(
    separator: String = ", ",
    prefix: String = "",
    postfix: String = ""
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

fun main() {
    val list = arrayListOf(1, 2, 3)
    println(list.joinToString(" "))
}
/* Output:
1 2 3
 */
