package ch05.ex2_1_3_FilterMap2

/**
 * @author runningpig66
 * @date 2025/11/28 周五
 * @time 1:19
 * §5.2 集合的函数式 API §5.2.1 基础：filter 和 map
 * P.117 代码清单 map
 *
 * public inline fun <T, R> Iterable<T>.map(transform: (T) -> R): List<R>
 */
fun main() {
    val list = listOf(1, 2, 3, 4)
    // map: 对每个元素应用转换函数，返回新的 List，元素类型为转换结果
    println(list.map { it * it })
}
/* Output:
[1, 4, 9, 16]
 */
