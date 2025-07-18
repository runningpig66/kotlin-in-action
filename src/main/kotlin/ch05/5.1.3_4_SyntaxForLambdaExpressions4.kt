package ch05.ex1_3_4_SyntaxForLambdaExpressions4

// 代码清单 P.111
fun main() {
    val sum = { x: Int, y: Int ->
        println("Computing the sum of $x and $y...")
        x + y
    }
    println(sum(1, 2))
}
/** Output:
 * Computing the sum of 1 and 2...
 * 3
 */