package ch03.ex1_1_CreatingCollectionsInKotlin

val set = hashSetOf(1, 7, 53)
val list = arrayListOf(1, 7, 53)
val map = hashMapOf(1 to "one", 7 to "seven", 53 to "fifty-three")

fun main() {
    println(set.javaClass)
    println(list.javaClass)
    println(map.javaClass)
}
/** Output:
 * class java.util.HashSet
 * class java.util.ArrayList
 * class java.util.HashMap
 */