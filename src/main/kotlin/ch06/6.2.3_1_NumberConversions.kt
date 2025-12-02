package ch06.ex2_3_1_NumberConversions

/**
 * @author runningpig66
 * @date 2025/12/2 周二
 * @time 11:18
 * §6.2 基本数据类型和其他基本类型 §6.2.3 数字转换
 * P.161 代码清单
 *
 * Kotlin 和 Java 之间一条重要的区别就是处理数字转换的方式。Kotlin 不会自动地把数字从一种类型转换成另外一种，
 * 即便是转换成范围更大的类型。Kotlin 要求你显式地转换类型，这样只有类型相同的值才能比较：
 */
fun main() {
    val x = 1
    println(x.toLong() in listOf(1L, 2L, 3L))
}
/* Output:
true
 */
