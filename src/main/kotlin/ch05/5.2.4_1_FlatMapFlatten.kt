package ch05.ex2_4_1_FlatMapFlatten

// 代码清单 P.120
fun main() {
    val strings = listOf("abc", "def")
    // flatMap: 对每个元素映射为集合并展开所有结果成一个扁平列表
    println(strings.flatMap { it.toList() })
    println(strings.map { it.toList() })
    println("abc".toList())
}
/** Output:
 * [a, b, c, d, e, f]
 * [[a, b, c], [d, e, f]]
 * [a, b, c]
 */