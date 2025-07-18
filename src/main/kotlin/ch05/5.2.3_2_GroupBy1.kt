package ch05.ex2_3_2_GroupBy1

// 代码清单 P.120
fun main() {
    val list = listOf("a", "ab", "b")
    println(list.groupBy(String::first))
}
/** Output:
 * {a=[a, ab], b=[b]}
 */