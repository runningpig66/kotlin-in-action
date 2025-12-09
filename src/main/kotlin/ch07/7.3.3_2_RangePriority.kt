package ch07.ex3_3_2_RangePriority

/**
 * @author runningpig66
 * @date 2025/12/8 周一
 * @time 21:50
 * §7.3 集合与区间的约定 §7.3.3 rangeTo 的约定
 * P.192 代码清单
 *
 * rangeTo 运算符的优先级低于算术运算符，但是最好把参数括起来以免混淆。
 * 还要注意，表达式 0..n.forEach 不会被编译，因为必须把区间表达式括起来才能调用它的方法。
 */
fun main() {
    val n = 9
    // 可以写成 0..n + 1, 但括起来更清晰一点
    println(0..(n + 1))
    // 把区间括起来，来调用它的方法
    (0..n).forEach { print(it) }
}
/* Output:
0..10
0123456789
 */
