package ch06.ex3_5_4_Arrays3

// 代码清单 P.174
fun main() {
    val fiveZeros: IntArray = IntArray(5)
    val fiveZerosToo = intArrayOf(0, 0, 0, 0, 0)
    val squares = IntArray(5) { i -> (i + 1) * (i + 1) }
    println(fiveZeros.joinToString())
    println(fiveZerosToo.joinToString())
    println(squares.joinToString())
}
/** Output:
 * 0, 0, 0, 0, 0
 * 0, 0, 0, 0, 0
 * 1, 4, 9, 16, 25
 */