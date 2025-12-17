package ch08.ex1_4_2_DefaultAndNullValuesForParametersWithFunctionTypes1

import ch08.ex1_4_1_JoinToStringDefault.joinToString

/**
 * @author runningpig66
 * @date 2025/12/16 周二
 * @time 0:12
 * §8.1 声明高阶函数 §8.1.4 函数类型的参数默认值和 null 值
 * P.214 代码清单 8.5 使用函数类型的可空参数
 *
 * 另一种选择是声明一个参数为可空的函数类型。注意这里不能直接调用作为参数传递进来的函数，
 * Kotlin 会因为检测到潜在的空指针异常而导致编译失败。一种可选的办法是显式地检查 null；
 *
 * 还有一个更简单的版本，它利用了这样一个事实，函数类型是一个包含 invoke 方法的接口的具体实现。
 * 作为一个普通方法，invoke 可以通过安全调用语法被调用：callback?.invoke()。
 * public interface Function1<in P1, out R> : Function<R> {
 *     /** Invokes the function with the specified argument. */
 *     public operator fun invoke(p1: P1): R
 * }
 * 下面介绍使用这项技术重写 joinToString 函数。
 */
fun <T> Collection<T>.joinToString(
    separator: String = ", ",
    prefix: String = "",
    postfix: String = "",
    transform: ((T) -> String)? = null // 声明一个函数类型的可空参数
): String {
    val result = StringBuilder(prefix)
    for ((index, element) in this.withIndex()) {
        if (index > 0) {
            result.append(separator)
        }
        val str = transform?.invoke(element) // 使用安全调用语法调用函数
            ?: element.toString() // 使用 Elvis 运算符处理回调没有被指定的情况
        result.append(str)
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
