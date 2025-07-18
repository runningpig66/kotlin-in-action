package ch05.ex2_4_2_FlatMapFlatten1

// 代码清单 P.121
class Book(val title: String, val author: List<String>)

fun main() {
    val books = listOf(
        Book("Thursday Next", listOf("Jasper Fforde")),
        Book("Mort", listOf("Terry Pratchett")),
        Book("Good Omens", listOf("Terry Pratchett", "Neil Gaiman"))
    )
    println(books.flatMap { it.author }.toSet())

    val authorsList: List<List<String>> = books.map { it.author }
    // flatten: 将嵌套集合结构展平成一个单一列表
    val authorList: List<String> = authorsList.flatten()
    println(authorList)
}
/** Output:
 * [Jasper Fforde, Terry Pratchett, Neil Gaiman]
 * [Jasper Fforde, Terry Pratchett, Terry Pratchett, Neil Gaiman]
 */