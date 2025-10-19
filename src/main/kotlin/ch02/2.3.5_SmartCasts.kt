package ch02.ex3_5_SmartCasts

/**
 * @author runningpig66
 * @date 2025/10/14 周二
 * @time 5:37
 * P.32 代码清单 2.17 表达式类层次结构
 * P.33 代码清单 2.18 使用 if 层叠对表达式求值
 */
interface Expr

// 简单的值对象类，只有一个属性 value, 实现了 Expr 接口
class Num(val value: Int) : Expr

// Sum 运算的实参可以是任何 Expr: Num 或者另一个 Sum
class Sum(val left: Expr, val right: Expr) : Expr

fun eval(e: Expr): Int {
    if (e is Num) {
        val n = e as Num // 显式地转换成类型 Num 是多余
        return n.value
    }
    if (e is Sum) {
        return eval(e.left) + eval(e.right) // 变量 e 被智能地转换了类型
    }
    throw IllegalArgumentException("Unknown expression")
}

fun main() {
    println(eval(Sum(Sum(Num(1), Num(2)), Num(4))))
}
/* Output:
7
 */
