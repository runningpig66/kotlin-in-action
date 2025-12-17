package ch08.ex1_4_1_JoinToStringDefault

/**
 * @author runningpig66
 * @date 2025/12/15 周一
 * @time 23:00
 * §8.1 声明高阶函数 §8.1.4 函数类型的参数默认值和 null 值
 * P.213 代码清单 8.4 给函数类型的参数指定默认值
 *
 * 我们现在已经知道可以传递一个 lambda 去指定如何将对象转换为字符串。但是要求所有调用者都传递 lambda 是比较烦人的事情，
 * 因为大部分调用者使用默认的行为就可以了。为了解决这个问题，可以定义一个函数类型的参数并用一个 lambda 作为它的默认值。
 */
fun <T> Collection<T>.joinToString(
    separator: String = ", ",
    prefix: String = "",
    postfix: String = "",
    transform: (T) -> String = { it.toString() } // 声明一个以 lambda 为默认值的函数类型的参数
): String {
    val result = StringBuilder(prefix)
    for ((index, element) in this.withIndex()) {
        if (index > 0) {
            result.append(separator)
        }
        // 调用作为实参传递给 "transform" 形参的函数
        result.append(transform(element))
    }
    result.append(postfix)
    return result.toString()
}

fun main() {
    val letters = listOf("Alpha", "Beta")
    // 使用默认的转换函数
    println(letters.joinToString())
    // 传递一个 lambda 作为参数
    println(letters.joinToString { it.lowercase() })
    // 使用命名参数语法传递几个参数，包括一个 lambda
    println(letters.joinToString(separator = "! ", postfix = "! ", transform = { it.uppercase() }))
}
/* Output:
Alpha, Beta
alpha, beta
ALPHA! BETA!
 */
