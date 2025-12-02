package ch06.ex3_5_2_Arrays1

/**
 * @author runningpig66
 * @date 2025/12/2 周二
 * @time 20:38
 * §6.3 集合与数组 §6.3.5 对象和基本数据类型的数组
 * P.173 代码清单 6.30 创建对象类型的数组
 *
 * Kotlin 中的一个数组是一个带有类型参数的类，其元素类型被指定为相应的类型参数。
 * 要在 Kotlin 中创建数组，有下面这些方法供你选择：
 * - arrayOf 函数创建一个数组，它包含的元素是指定为该函数的实参。
 * - arrayOfNulls 创建一个给定大小的数组，包含的是 null 元素。当然，它只能用来创建包含元素类型可空的数组。
 * - Array 构造方法接收数组的大小和一个 lambda 表达式，调用 lambda 表达式来创建每一个数组元素。
 * 这就是使用非空元素类型来初始化数组，但不用显式地传递每个元素的方式。
 *
 * public actual inline fun <reified T> arrayOf(vararg elements: T): Array<T>
 * public actual fun <reified T> arrayOfNulls(size: Int): Array<T?>
 * Array<T>.public actual inline constructor(size: Int, init: (Int) -> T)
 */
fun main() {
    val array1: Array<Char> = arrayOf('a', 'b', 'c')
    val array2: Array<Char?> = arrayOfNulls(3)
    // 这里有一个简单的例子，展示了如何使用 Array 函数来创建从 "a" 到 "z" 的字符串数组。
    // Lambda 接收数组元素的下标并返回放在数组下标位置的值。
    val letters: Array<String> = Array(26) { index ->
        ('a' + index).toString()
    }
    println(array1.joinToString())
    println(array2.joinToString())
    println(letters.joinToString(""))
}
/* Output:
a, b, c
null, null, null
abcdefghijklmnopqrstuvwxyz
 */
