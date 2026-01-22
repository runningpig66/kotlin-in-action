package ch11.printFirst

/**
 * @author runningpig66
 * @date 2026/1/19 周一
 * @time 11:10
 */
// Every list is a possible argument.
fun printFirst(list: List<*>) {
    // isNotEmpty() doesn’t use the generic type parameter.
    if (list.isNotEmpty()) {
        // first() now returns Any?, but in this case, that’s enough.
        println(list.first())
    }
}

fun main() {
    printFirst(listOf("Sveta", "Seb", "Dima", "Roman"))
}
/* Output:
Sveta
 */
