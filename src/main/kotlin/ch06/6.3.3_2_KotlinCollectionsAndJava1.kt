package ch06.ex3_3_2_KotlinCollectionsAndJava1

import ch06.CollectionUtils

// 代码清单 P.170
fun printInUppercase(list: List<String>) {
    println(CollectionUtils.uppercaseAll(list))
    println(list.first())
}

fun main() {
    val list = listOf("a", "b", "c")
    printInUppercase(list)
}
/** Output:
 * [A, B, C]
 * A
 */