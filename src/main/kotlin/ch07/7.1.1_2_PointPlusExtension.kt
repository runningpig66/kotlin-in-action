package ch07.PointPlusExtension

/**
 * @author runningpig66
 * @date 2025/12/3 周三
 * @time 11:15
 * §7.1 重载算术运算符 §7.1.1 重载二元算术运算
 * P.181 代码清单 7.2 把运算符定义为扩展函数
 *
 * 在使用了 operator 修饰符声明了 plus 函数之后，你就可以直接使用 + 号来求和了。+ 号运算将会转换为 plus 函数的调用。
 * 除了把这个运算符声明为一个成员函数外，也可以把它定义为一个扩展函数。这是给第三方库的类定义约定扩展函数的常用模式。
 */
data class Point(val x: Int, val y: Int)

operator fun Point.plus(other: Point): Point {
    return Point(x + other.x, y + other.y)
}

fun main() {
    val p1 = Point(10, 20)
    val p2 = Point(30, 40)
    println(p1 + p2)
}
/* Output:
Point(x=40, y=60)
 */
