package ch06.ex3_5_4_Arrays3

/**
 * @author runningpig66
 * @date 2025/12/2 周二
 * @time 21:48
 * §6.3 集合与数组 §6.3.5 对象和基本数据类型的数组
 * P.174 代码清单 创建基本数据类型的数组
 *
 * 为了表示基本数据类型的数组，Kotlin 提供了若干独立的类，每一种基本数据类型都对应一个。
 * 例如，Int 类型值的数组叫作 IntArray. 这些数组中的值存储时并没有装箱，而是使用了可能的最高效的方式。
 * 要创建一个基本数据类型的数组，你有如下选择：
 * - 该类型的构造方法接收 size 参数并返回一个使用对应基本数据类型默认值（通常是 0）初始化好的数组。
 * - 工厂函数（IntArray 的 intArrayOf, 以及其他数组类型的函数）接收变长参数的值并创建存储这些值的数组。
 * - 另一种构造方法，接收一个大小和一个用来初始化每个元素的 lambda.
 *
 * IntArray.public actual constructor(size: Int)
 * public actual fun intArrayOf(vararg elements: Int): IntArray
 * IntArray.public actual inline constructor(size: Int, init: (Int) -> Int)
 *
 * public fun Array<out Int>.toIntArray(): IntArray
 * public fun Collection<Int>.toIntArray(): IntArray
 */
fun main() {
    // 下面是创建存储了 5 个 0 的整型数组的两种选择：
    val fiveZeros: IntArray = IntArray(5)
    val fiveZeroToo: IntArray = intArrayOf(0, 0, 0, 0, 0)
    // 下面是接收 lambda 的构造方法的例子：
    val squares = IntArray(5) { index -> (index + 1) * (index + 1) }
    println(fiveZeros.joinToString())
    println(fiveZeroToo.joinToString())
    println(squares.joinToString())

    // 或者，假如你有一个持有基本数据类型装箱后的值的数组或者集合，
    // 可以用对应的转换函数把它们转换成基本数据类型的数组，比如 toIntArray.
    val IntArray: Array<Int> = arrayOf(1, 2, 3)
    val intArray1: IntArray = IntArray.toIntArray()
    val IntList: List<Int> = listOf(1, 2, 3)
    val intArray2: IntArray = IntList.toIntArray()
}
/* Output:
0, 0, 0, 0, 0
0, 0, 0, 0, 0
1, 4, 9, 16, 25
 */
