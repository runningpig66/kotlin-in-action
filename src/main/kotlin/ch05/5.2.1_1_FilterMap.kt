package ch05.ex2_1_1_FilterMap

/**
 * @author runningpig66
 * @date 2025/11/28 周五
 * @time 0:56
 * §5.2 集合的函数式 API §5.2.1 基础：filter 和 map
 * P.116 代码清单 filter
 *
 * public inline fun <T> Iterable<T>.filter(predicate: (T) -> Boolean): List<T>
 */
fun main() {
    val list = listOf(1, 2, 3, 4)
    // filter: 根据给定条件过滤集合，返回符合条件元素组成的新 List
    println(list.filter { it % 2 == 0 }) // 只有偶数留了下来
    // 上面的结果是一个新的集合，它只包含输入集合中那些满足判断式的元素，原集合不变。
    println(list)
}
/* Output:
[2, 4]
[1, 2, 3, 4]
 */
