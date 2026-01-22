package ch11.ex6_2_StarProjection1

/**
 * @author runningpig66
 * @date 2026/1/19 周一
 * @time 11:14
 */
// Again, every list is a possible argument.
fun <T> printFirst(list: List<T>) {
    if (list.isNotEmpty()) {
        // first() now returns a value of T.
        println(list.first())
    }
}

fun main() {
    printFirst(listOf("Sveta", "Seb", "Dima", "Roman"))
}
/* Output:
Sveta
 */
