package ch04.ex1_5_1_SealedClasses

/**
 * @author runningpig66
 * @date 2025/11/22 周六
 * @time 16:42
 * §4.1 定义类继承结构 §4.1.5 密封类：定义受限的类继承结构
 * P.79 代码清单 4.12 作为接口实现的表达式
 */
interface Expr
class Num(val value: Int) : Expr
class Sum(val left: Expr, val right: Expr) : Expr

fun eval(e: Expr): Int =
    when (e) {
        is Num -> e.value
        is Sum -> eval(e.left) + eval(e.right)
        // 必须检查 "else" 分支
        else -> throw IllegalArgumentException("Unknown expression")
    }

fun main() {
    println(eval(Sum(Sum(Num(1), Num(2)), Num(4))))
}
/* Output:
7
 */
