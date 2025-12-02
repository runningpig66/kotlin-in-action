package ch06.ex3_5_3_Arrays2

/**
 * @author runningpig66
 * @date 2025/12/2 周二
 * @time 21:10
 * §6.3 集合与数组 §6.3.5 对象和基本数据类型的数组
 * P.174 代码清单 6.31 向 vararg 方法传递集合
 *
 * Kotlin 代码中最常见的创建数组的情况之一是需要调用参数为数组的 Java 方法时，或是调用带有 vararg 参数的 Kotlin 函数时。
 * 在这些情况下，通常已经将数据存储在集合中，只需将其转换为数组即可。可以使用 toTypedArray 方法来执行此操作。
 *
 * public inline fun String.format(vararg args: Any?): String = java.lang.String.format(this, *args)
 * public actual inline fun <reified T> Collection<T>.toTypedArray(): Array<T>
 */
fun main() {
    val strings: List<String> = listOf("a", "b", "c")
    // 期望 vararg 参数时使用展开运算符 (*) 传递数组
    println("%s/%s/%s".format(*strings.toTypedArray()))
}
/* Output:
a/b/c
 */
