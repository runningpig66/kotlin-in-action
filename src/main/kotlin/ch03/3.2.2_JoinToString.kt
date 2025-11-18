package ch03

/**
 * @author runningpig66
 * @date 2025/11/19 周三
 * @time 1:56
 * 3.2 让函数更好调用：3.2.2 默认参数值
 * P.49 代码清单 3.2 声明带默认参数值的 joinToString()
 *
 * 在 Kotlin 中，可以在声明函数的时候，指定参数的默认值，这样就可以避免创建重载的函数。
 * 让我们尝试改进一下前面的 joinToString 函数。在大多数情况下，
 * 字符串可以不加前缀或者后缀并用逗号分隔。所以，我们把这些设置为默认值。
 */
@JvmOverloads
fun <T> joinToString(
    collection: Collection<T>,
    separator: String = ", ", // 有默认值的参数
    prefix: String = "",
    postfix: String = ""
): String {
    val result = StringBuilder(prefix)
    for ((index, element) in collection.withIndex()) {
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
    // 现在可以用所有参数来调用这个函数，或者省略掉部分参数：
    println(joinToString(list, ", ", "", ""))
    println(joinToString(list))
    println(joinToString(list, "; "))
    // 当使用常规的调用语法时，必须按照函数声明中定义的参数顺序来给定参数，可以省略的只有排在末尾的参数。
    // 如果使用命名参数，可以省略中间的一些参数，也可以以你想要的任意顺序只给定你需要的参数：
    println(joinToString(list, postfix = ";", prefix = "# "))
}
/* Output:
1, 2, 3
1, 2, 3
1; 2; 3
# 1, 2, 3;
 */
