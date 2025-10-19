package ch02.EvalIf

/**
 * @author runningpig66
 * @date 2025/10/14 周二
 * @time 7:00
 * P.34 代码清单 2.19 使用有返回值的 if 表达式
 */
interface Expr
class Num(val value: Int) : Expr
class Sum(val left: Expr, val right: Expr) : Expr

fun eval(e: Expr): Int =
    if (e is Num) e.value
    else if (e is Sum) eval(e.right) + eval(e.left)
    else throw IllegalArgumentException("Unknown expression")

fun main() {
    println(eval(Sum(Num(1), Num(2))))
}
/* Output:
3
 */
