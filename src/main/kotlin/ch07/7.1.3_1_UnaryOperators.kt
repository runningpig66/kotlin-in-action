package ch07.ex1_3_1_UnaryOperators

/**
 * @author runningpig66
 * @date 2025/12/3 周三
 * @time 18:32
 * §7.1 重载算术运算符 §7.1.3 重载一元运算符
 * P.185 代码清单 7.5 定义一个一元运算符
 *
 * 可重载的一元算法的运算符
 * 表达式       |    函数名
 * +a          |    unaryPlus
 * -a          |    unaryMinus
 * !a          |    not
 * ++a, a++    |    inc
 * --a, a--    |    dec
 *
 * 重载一元运算符的过程与你在前面看到的方式相同：用预先定义的一个名称来声明函数（成员函数或扩展函数），
 * 并用修饰符 operator 标记。用于重载一元运算符的函数，没有任何参数。我们来看一个例子。
 */
data class Point(val x: Int, val y: Int)

// 一元运算符无参数
operator fun Point.unaryMinus(): Point {
    // 坐标取反，然后返回
    return Point(-x, -y)
}

fun main() {
    val p = Point(10, 20)
    // 一元运算符 - 被转换为 unaryMinus 函数的调用
    println(-p)
}
/* Output:
Point(x=-10, y=-20)
 */
