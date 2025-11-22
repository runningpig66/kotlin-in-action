package ch04.ex1_5_2_SealedClasses1

/**
 * @author runningpig66
 * @date 2025/11/22 周六
 * @time 16:54
 * §4.1 定义类继承结构 §4.1.5 密封类：定义受限的类继承结构
 * P.79 代码清单 4.13 作为密封类的表达式
 *
 * notes: SealedClasses.md
 * Kotlin 1.5+ 放宽了密封类定义规则：
 * “子类位置”规则已经更新（同 module + 同 package）；
 * 现在有 sealed interface，适合用来做 UI 事件、结果类型等根接口。
 */
// 用 sealed 修饰符将基类标记为密封类，sealed 修饰符隐含的这个类是一个 open 类
sealed class Expr {
    // 将所有可能的子类作为嵌套类列出
    class Num(val value: Int) : Expr()
    class Sum(val left: Expr, val right: Expr) : Expr()
}

fun eval(e: Expr): Int =
    when (e) { // "when" 表达式涵盖了所有可能的情况，所以不再需要 "else" 分支
        is Expr.Num -> e.value
        is Expr.Sum -> eval(e.left) + eval(e.right)
    }

fun main() {
    println(eval(Expr.Sum(Expr.Sum(Expr.Num(1), Expr.Num(2)), Expr.Num(4))))
}
/* Output:
7
 */
