package ch05.ex1_4_2_AccessingVariablesInScope2

// 代码清单 5.11 在 lambda 中改变局部变量2
fun makeCounter(): () -> Int {
    var counter = 0
    return { ++counter }
}

fun main() {
    val lambda = makeCounter()
    println(lambda.invoke())
    println(lambda.invoke())
    println(lambda.invoke())
}
/** Output:
 * 1
 * 2
 * 3
 */