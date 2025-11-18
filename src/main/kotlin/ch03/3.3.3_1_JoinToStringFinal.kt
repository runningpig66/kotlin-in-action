package ch03.JoinToStringFinal

/**
 * @author runningpig66
 * @date 2025/11/19 周三
 * @time 3:50
 * 3.3 给别人的类添加方法：扩展函数和属性：3.3.3 作为扩展函数的工具函数
 * P.55 代码清单 3.4 声明扩展函数 joinToString()
 *
 * 现在，可以写一个 joinToString 函数的终极版本了，它和你在 Kotlin标准库中看到的一模一样。
 */

fun <T> Collection<T>.joinToString( // 为 Collection<T> 声明一个扩展函
    separator: String = ", ", // 为参数赋默认值
    prefix: String = "",
    postfix: String = ""
): String {
    val result = StringBuilder(prefix)
    for ((index, element) in this.withIndex()) { // this 指向接收者对象：T 的集合
        if (index > 0) {
            result.append(separator)
        }
        result.append(element)
    }
    result.append(postfix)
    return result.toString()
}

fun main() {
    val list = listOf(1, 2, 3)
    println(list.joinToString(separator = "; ", prefix = "(", postfix = ")"))
    println(list.joinToString(" "))
}
/* Output:
(1; 2; 3)
1 2 3
 */
