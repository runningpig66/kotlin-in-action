package ch03.ex5_1_2_SplittingStrings1

fun main() {
    println("12.345-6.A".split(".", "-"))
    println("12.345-6.A".split('.', '-'))
}
/** Output:
 * [12, 345, 6, A]
 * [12, 345, 6, A]
 */