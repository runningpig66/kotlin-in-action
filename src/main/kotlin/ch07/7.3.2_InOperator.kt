package ch07.ex3_2_InOperator

/**
 * @author runningpig66
 * @date 2025/12/8 周一
 * @time 19:13
 * §7.3 集合与区间的约定 §7.3.2 "in" 的约定
 * P.190 代码清单 7.11 实现 in 约定
 *
 * 集合支持的另一个运算符是 in 运算符。用于检查某个对象是否属于集合。
 * 对应的函数叫作 contains。我们来实现一下，使用 in 运算符来检查点是否属于一个矩形。
 */
data class Point(val x: Int, val y: Int)
data class Rectangle(val upperLeft: Point, val lowerRight: Point)

operator fun Rectangle.contains(p: Point): Boolean {
    // 构建一个区间，检查坐标“x”是否属于这个区间；使用“until”函数来构建一个开区间
    return p.x in upperLeft.x until lowerRight.x &&
            p.y in upperLeft.y until lowerRight.y
}

fun main() {
    val rect = Rectangle(Point(10, 20), Point(50, 50))
    // in 操作将会转换为 contains 函数的调用；in 右边的对象将会调用 contains 函数，in 左边的对象将会作为函数入参。
    println(Point(20, 30) in rect)
    println(Point(5, 5) in rect)
}
/* Output:
true
false
 */
