package ch07.PointPlusMember

/**
 * @author runningpig66
 * @date 2025/12/3 周三
 * @time 11:06
 * §7.1 重载算术运算符 §7.1.1 重载二元算术运算
 * P.180 代码清单 7.1 定义一个 plus 运算符
 *
 * 在使用了 operator 修饰符声明了 plus 函数之后，你就可以直接使用 + 号来求和了。+ 号运算将会转换为 plus 函数的调用。
 */
data class Point(val x: Int, val y: Int) {
    // 定义一个名为 "plus" 的方法
    operator fun plus(other: Point): Point {
        // 坐标分别相加，然后返回一个新的点
        return Point(x + other.x, y + other.y)
    }
}

fun main() {
    val p1 = Point(10, 20)
    val p2 = Point(30, 40)
    // 通过使用 + 号来调用 "plus" 方法
    println(p1 + p2)
}
/* Output:
Point(x=40, y=60)
 */
