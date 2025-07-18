package ch05.ex1_3_1_SyntaxForLambdaExpressions

// 代码清单 P.108
fun main() {
    val sum = { x: Int, y: Int -> x + y }
    println(sum(1, 2));
    { println(42) }()
}
/** Output:
 * 3
 * 42
 */