package ch02.ex3_6_2_IfToWhen1

/**
 * @author runningpig66
 * @date 2025/10/14 周二
 * @time 7:08
 * P.35 代码清单 2.20 使用 when 代替 if 层叠
 */
interface Expr
class Num(val value: Int) : Expr
class Sum(val left: Expr, val right: Expr) : Expr

fun eval(e: Expr): Int =
    when (e) {
        // 检查实参类型的 “when” 分支
        is Num -> e.value // 这里 ← ↓ 应用了智能转换
        is Sum -> eval(e.right) + eval(e.left)
        else -> throw IllegalArgumentException("Unknown expression")
    }

fun main() {
    println(eval(Sum(Num(1), Num(2))))
}
/* Output:
3
 */
