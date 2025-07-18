package ch05.ex2_1_1_FilterMap

// 代码清单 P.116
fun main() {
    val list = listOf(1, 2, 3, 4)
    // filter: 根据给定条件过滤集合，返回符合条件元素组成的新 List
    println(list.filter { it % 2 == 0 })
}
/** Output:
 * [2, 4]
 */
