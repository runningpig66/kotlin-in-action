package ch03.ex1_1_CreatingCollectionsInKotlin

/**
 * @author runningpig66
 * @date 2025/11/19 周三
 * @time 1:09
 * 3.1 在 Kotlin 中创建集合
 * P.46 代码清单
 */

val set = hashSetOf(1, 7, 53)
val list = arrayListOf(1, 7, 53)
val map = hashMapOf(1 to "one", 7 to "seven", 53 to "fifty-three")

fun main() {
    println(set.javaClass) // Kotlin 的 javaClass 等价于 Java 的 getClass()
    println(list.javaClass)
    println(map.javaClass)
}
/* Output:
class java.util.Hashet
class java.util.ArrayList
class java.util.HashMap
 */
