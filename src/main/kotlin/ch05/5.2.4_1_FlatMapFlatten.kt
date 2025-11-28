package ch05.ex2_4_1_FlatMapFlatten

/**
 * @author runningpig66
 * @date 2025/11/28 周五
 * @time 15:49
 * §5.2 集合的函数式 API §5.2.4 flatMap 和 flatten：处理嵌套集合中的元素
 * P.120 代码清单
 *
 * flatMap 函数做了两件事情：首先根据作为实参给定的函数对集合中的每个元素做变换（或者说映射），
 * 然后把多个列表合并（或者说平铺）成一个列表。下面这个字符串的例子很好地阐明了这个概念。
 *
 * public fun CharSequence.toList(): List<Char>
 * public inline fun <T, R> Iterable<T>.map(transform: (T) -> R): List<R>
 * public inline fun <T, R> Iterable<T>.flatMap(transform: (T) -> Iterable<R>): List<R>
 */
fun main() {
    // 字符串上的 toList 函数把字符串转换成字符列表。
    println("abc".toList())
    // 如果和 toList 一起使用的是 map 函数，你会得到一个字符列表的列表。
    val strings: List<String> = listOf("abc", "def")
    val chatsList: List<List<Char>> = strings.map { it.toList() }
    println(chatsList)
    // flatMap: 对每个元素映射为集合并展开所有结果成一个扁平列表。
    val charsFlatList: List<Char> = strings.flatMap { it.toList() }
    println(charsFlatList)
}
/* Output:
[a, b, c]
[[a, b, c], [d, e, f]]
[a, b, c, d, e, f]
 */
