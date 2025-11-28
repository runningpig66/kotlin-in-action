package ch05.ex2_4_2_FlatMapFlatten1

/**
 * @author runningpig66
 * @date 2025/11/28 周五
 * @time 16:05
 * §5.2 集合的函数式 API §5.2.4 flatMap 和 flatten：处理嵌套集合中的元素
 * P.121 代码清单 flatMap flatten
 *
 * public inline fun <T, R> Iterable<T>.flatMap(transform: (T) -> Iterable<R>): List<R>
 * public fun <T> Iterable<Iterable<T>>.flatten(): List<T>
 */
class Book(val title: String, val author: List<String>)

fun main() {
    // 假设你有一堆藏书，使用 Book 类表示：每本书都可能有一个或者多个作者，可以统计出图书馆中的所有作者的 set:
    val books: List<Book> = listOf(
        Book("Thursday Next", listOf("Jasper Fforde")),
        Book("Mort", listOf("Terry Pratchett")),
        Book("Good Omens", listOf("Terry Pratchett", "Neil Gaiman"))
    )
    // flatMap 函数做了两件事情，首先根据作为实参给定的函数对集合中的每个元素做变换（或者说映射），
    // 然后把多个列表合并（或者说平铺）成一个列表。
    println(books.flatMap { it.author }.toSet()) // 包含撰写 "books" 集合中书籍的所有作者的 set

    val authorsList: List<List<String>> = books.map { it.author }
    // flatten: 将嵌套集合结构展平成一个单一列表
    // 如果你不需要做任何变换，只是需要平铺一个集合可以使用 flatten 函数：listOfLists.flatten().
    val authorList: List<String> = authorsList.flatten()
    println(authorList)
}
/* Output:
[Jasper Fforde, Terry Pratchett, Neil Gaiman]
[Jasper Fforde, Terry Pratchett, Terry Pratchett, Neil Gaiman]
 */
