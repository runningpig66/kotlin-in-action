package ch03.ex1_2_CreatingCollectionsInKotlin1

/**
 * @author runningpig66
 * @date 2025/11/19 周三
 * @time 1:32
 * 3.1 在 Kotlin 中创建集合
 * P.46 代码清单
 */

fun main() {
    val strings = listOf("first", "second", "fourteenth")
    println(strings.last())
    val numbers = setOf(1, 14, 2)
    println(numbers.max())
}
/* Output:
fourteenth
14
 */
