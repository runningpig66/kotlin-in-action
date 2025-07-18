package ch05.ex2_2_2_AllAnyCountFind1

// 代码清单 P.118
fun main() {
    val list = listOf(1, 2, 3)
    println(!list.all { it == 3 })
    println(list.any { it != 3 })
}
/** Output:
 * true
 * true
 */