package ch05.ex1_5_1_MemberReferences

/**
 * @author runningpig66
 * @date 2025/11/27 周四
 * @time 23:55
 * §5.1 Lambda 表达式和成员引用 §5.1.5 成员引用
 * P.114 代码清单 顶层函数引用
 */
fun salute() = println("Salute!")
fun main() {
    // 成员引用和调用该函数的 lambda 具有一样的类型，所以可以互换使用：
    // public inline fun <R> run(block: () -> R): R
    run(::salute) // 还可以引用顶层函数（不是类的成员）
}
/* Output:
Salute!
 */
