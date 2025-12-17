package ch08.ex1_4_0_JoinToStringHardcodedToString

/**
 * @author runningpig66
 * @date 2025/12/15 周一
 * @time 23:28
 * §8.1 声明高阶函数 §8.1.4 函数类型的参数默认值和 null 值
 * P.212 代码清单 8.3 使用了硬编码 toString 转换的 joinToString 函数
 *
 * 声明函数类型的参数的时候可以指定参数的默认值。要知道默认值的用处，我们回头看一看第 3 章讨论过的 joinToString 函数。以下是它的最终实现。
 *
 * 这个实现很灵活，但是它并没有让你控制转换的关键点：集合中的元素是如何转换为字符串的。
 * 代码中使用了 StringBuilder.append(o: Any?)，它总是使用 toString 方法将对象转换为字符串。在大多数情况下这样就可以了，但并不总是这样。
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
        // 使用默认的 toString 方法将对象转换为字符串
        result.append(element)
    }
    result.append(postfix)
    return result.toString()
}

fun main() {
    val letters = listOf("Alpha", "Beta")
    println(letters.joinToString())
}
/* Output;
Alpha, Beta
 */
