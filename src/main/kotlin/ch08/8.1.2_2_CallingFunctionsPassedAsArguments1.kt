package ch08.ex1_2_2_CallingFunctionsPassedAsArguments1

/**
 * @author runningpig66
 * @date 2025/12/13 周六
 * @time 21:34
 * §8.1 声明高阶函数 §8.1.2 调用作为参数的函数
 * P.210 代码清单 8.2 实现一个简单版本的 filter 函数
 *
 * filter 函数以一个判断式作为参数。判断式的类型是一个函数，以字符串作为参数并返回 boolean 类型的值。
 * 如果要让传递给判断式的字符串出现在最终返回的字符串中，判断式需要返回 true，反之返回 false。以下是这个方法的实现。
 */
fun String.filter(predicate: (Char) -> Boolean): String {
    val sb = StringBuilder()
    for (index in 0 until this.length) {
        val element: Char = get(index)
        // 调用作为参数传递给 "predicate" 的函数
        if (predicate(element)) {
            sb.append(element)
        }
    }
    return sb.toString()
}

fun main() {
    // 传递一个 lambda 作为 "predicate" 参数
    println("ab1c".filter { it in 'a'..'z' })
}
/* Output:
abc
 */
