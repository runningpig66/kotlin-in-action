package ch06.ex3_5_5_Arrays4

/**
 * @author runningpig66
 * @date 2025/12/2 周二
 * @time 22:41
 * §6.3 集合与数组 §6.3.5 对象和基本数据类型的数组
 * P.175 代码清单 6.32 对数组使用 forEachIndexed
 * {Program arguments: 1 2 3 4}
 *
 * 接下来，我们来看一下你可以对数组做的事情。除了那些基本操作（获取数组的长度，获取或者设置元素）外，
 * Kotlin 标准库支持一套和集合相同的用于数组的扩展函数。第 5 章中你看到的全部函数（filter, map 等）
 * 也适用于数组、包括基本数据类型的数组（注意，这些方法的返回值是列表而不是数组）
 *
 * 我们来看看如何使用 forEachIndexed 函数加上 lambda 来重写代码清单 6.29 中的代码。
 *
 * public inline fun <T> kotlin.Array<out T>.forEachIndexed(action: (kotlin.Int, T) -> kotlin.Unit): kotlin.Unit
 */
fun main(args: Array<String>) {
    args.forEachIndexed { index: Int, element: String ->
        println("Argument $index is: $element")
    }
}
/* Output:
Argument 0 is: 1
Argument 1 is: 2
Argument 2 is: 3
Argument 3 is: 4
 */
