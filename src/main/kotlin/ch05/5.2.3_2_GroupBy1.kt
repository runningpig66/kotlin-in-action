package ch05.ex2_3_2_GroupBy1

/**
 * @author runningpig66
 * @date 2025/11/28 周五
 * @time 15:36
 * §5.2 集合的函数式 API §5.2.3 groupBy: 把列表转换成分组的 map
 * P.120 代码清单
 *
 * 我们再来看另外一个例子，如何使用成员引用把字符串按照首学母分组：
 *
 * public inline fun <T, K> Iterable<T>.groupBy(keySelector: (T) -> K): Map<K, List<T>>
 * public fun CharSequence.first(): Char
 */
fun main() {
    val list: List<String> = listOf("a", "ab", "b")
    // 注意，这里 first 并不是 String 类的成员，而是一个扩展。然而，可以把它当作成员引用访问。
    println(list.groupBy { string -> string.first() })
    println(list.groupBy(String::first))
}
/* Output:
{a=[a, ab], b=[b]}
{a=[a, ab], b=[b]}
 */
