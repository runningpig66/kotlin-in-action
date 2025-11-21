package ch03.ex4_2_Varargs

/**
 * @author runningpig66
 * @date 2025/11/19 周三
 * @time 5:01
 * §3.4 处理集合：可变参数、中缀调用和库的支持 §3.4.2 可变参数：让函数支持任意数量的参数
 * P.60 代码清单
 * {Program arguments: arg1 arg2 arg3}
 *
 * Kotlin 和 Java 之间的另一个区别是，当需要传递的参数已经包装在数组中时，调用该函数的语法。
 * 在 Java 中，可以按原样传递数组，而 Kotlin 则要求你显式地解包数组，以便每个数组元素在函数中能作为单独的参数来调用。
 * 从技术的角度来讲，这个功能被称为展开运算符，而使用的时候，不过是在对应的参数前面放一个 * .
 */
fun main(args: Array<String>) {
    // 通过展开运算符 * , 可以在单个调用中组合来自数组的值和某些固定值。这在Java中并不支持。
    val list = listOf("args: ", *args) // 展开运算符 * 展开数组内容
    println(list)
}
/* Output:
[args: , arg1, arg2, arg3]
 */
