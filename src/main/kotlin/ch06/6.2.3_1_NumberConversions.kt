package ch06.ex2_3_1_NumberConversions

// 代码清单 P.161
fun main() {
    val x = 1
    // Kotlin 要求你显式地转换类型，这样只有类型相同的值才能比较
    println(x.toLong() in listOf(1L, 2L, 3L))
}
/** Output:
 * true
 */