package ch03.JoinToString

/**
 * @author runningpig66
 * @date 2025/11/19 周三
 * @time 1:42
 * §3.2 让函数更好调
 * P.47 代码清单 3.1 joinToString() 的基本实现
 */

fun <T> joinToString(
    collection: Collection<T>,
    separator: String,
    prefix: String,
    postfix: String
): String {
    val result = StringBuilder(prefix)
    for ((index, element) in collection.withIndex()) {
        if (index > 0) { // 不用在第一个元素前添加分隔符
            result.append(separator)
        }
        result.append(element)
    }
    result.append(postfix)
    return result.toString()
}

fun main() {
    val list = listOf(1, 2, 3)
    println(
        joinToString(
            list,
            separator = "; ",
            prefix = "(",
            postfix = ")"
        )
    )
}
/* Output:
(1; 2; 3)
 */
