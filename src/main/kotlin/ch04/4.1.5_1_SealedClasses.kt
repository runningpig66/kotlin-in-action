package ch04.ex1_5_1_SealedClasses

// 代码清单 4.12 作为接口实现的表达式
interface Expr
class Num(val value: Int) : Expr
class Sum(val left: Expr, val right: Expr) : Expr

fun eval(e: Expr): Int =
    when (e) {
        is Num -> e.value
        is Sum -> eval(e.left) + eval(e.right)
        else -> throw IllegalArgumentException("Unknown expression")
    }

fun main() {
    println(eval(Sum(Sum(Num(1), Num(2)), Num(4))))
}
/** Output:
 * 7
 */