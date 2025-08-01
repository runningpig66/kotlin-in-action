package ch03

@JvmOverloads
fun <T> joinToString(
    collection: Collection<T>,
    separator: String = ", ",
    prefix: String = "",
    postfix: String = ""
): String {
    val result = StringBuilder(prefix)

    for ((index, element) in collection.withIndex()) {
        if (index > 0) result.append(separator)
        result.append(element)
    }

    result.append(postfix)
    return result.toString()
}

fun main() {
    val list = listOf(1, 2, 3)

    println(joinToString(list, ", ", "", ""))
    println(joinToString(list))
    println(joinToString(list, "; "))
}
/** Output:
 * 1, 2, 3
 * 1, 2, 3
 * 1; 2; 3
 */