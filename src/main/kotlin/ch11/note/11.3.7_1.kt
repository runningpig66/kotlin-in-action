package ch11.note_1

/**
 * @author runningpig66
 * @date 2026/1/22 周四
 * @time 21:02
 * 11.3.7 Type aliases
 * 11.3.7 类型别名
 */
// A type alias is defined using the typealias keyword, the alias, and the underlying type.
typealias NameCombiner = (String, String, String, String) -> String

// Type aliases can be used wherever you would’ve used the underlying type, like variable declarations ...
val authorsCombiner: NameCombiner = { a, b, c, d -> "$a et al." }
val bandCombiner: NameCombiner = { a, b, c, d -> "$a, $b & The Gang" }

// ... or function parameter declarations.
fun combineAuthors(combiner: NameCombiner) {
    println(combiner("Sveta", "Seb", "Dima", "Roman"))
}

fun main() {
    // The type alias resolves to the underlying type. So it’s perfectly fine to pass a NameCombiner ...
    combineAuthors(bandCombiner)
    combineAuthors(authorsCombiner)
    // ... or a lambda taking four strings and returning a single string.
    combineAuthors { a, b, c, d -> "$d, $c & Co." }
}
/* Output:
Sveta, Seb & The Gang
Sveta et al.
Roman, Dima & Co.
 */
