package ch05.ex2_1_3_FilterMap2

// 代码清单 P.117
fun main() {
    val list = listOf(1, 2, 3, 4)
    // map: 对每个元素应用转换函数，返回新的 List，元素类型为转换结果
    println(list.map { it * it })
}
/** Output:
 * [1, 4, 9, 16]
 */