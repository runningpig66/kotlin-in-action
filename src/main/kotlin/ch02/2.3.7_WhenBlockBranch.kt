package ch02.ex3_7_WhenBlockBranch

/**
 * @author runningpig66
 * @date 2025/10/14 周二
 * @time 7:27
 * P.35 代码清单 2.21 使用分支中含有混合操作的 when
 */
interface Expr
class Num(val value: Int) : Expr
class Sum(val left: Expr, val right: Expr) : Expr

fun evalWithLogging(e: Expr): Int =
    when (e) {
        is Num -> {
            println("num: ${e.value}")
            e.value // 代码块中最后的表达式，如果 e 的类型是 Num 就会返回它
        }

        is Sum -> {
            val left = evalWithLogging(e.left)
            val right = evalWithLogging(e.right)
            println("sum: $left + $right")
            left + right // 如果 e 的类型是 Sum 就会返回这个表达式
        }

        else -> throw IllegalArgumentException("Unknown expression")
    }

fun main() {
    println(evalWithLogging(Sum(Sum(Num(1), Num(2)), Num(4))))
}
/* Output:
num: 1
num: 2
sum: 1 + 2
num: 4
sum: 3 + 4
7
 */
