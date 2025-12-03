package ch07.ex1_1_3_BinaryOperators2

/**
 * @author runningpig66
 * @date 2025/12/3 周三
 * @time 11:25
 * §7.1 重载算术运算符 §7.1.1 重载二元算术运算
 * P.182 代码清单 7.3 定义一个运算数类型不同的运算符
 *
 * 当你在定义一个运算符的时候，不要求两个运算数是相同的类型。
 * 例如，让我们定义一个运算符，它允许你用一个数字来缩放一个点，可以用它在不同坐标系之间做转换。
 *
 * 注意，Kotlin 运算符不会自动支持交换性（交换运算符的左右两边）。如果希望用户能够使用 1.5 * p 以外，
 * 还能使用 p * 1.5, 你需要为它定义一个单独的运算符：operator fun Double.times(p: Point): Point
 */
data class Point(val x: Int, val y: Int)

operator fun Point.times(scale: Double): Point {
    return Point((x * scale).toInt(), (y * scale).toInt())
}

fun main() {
    val p1 = Point(10, 20)
    val p2: Point = p1 * 1.5
    println(p2)
}
/* Output:
Point(x=15, y=30)
 */
