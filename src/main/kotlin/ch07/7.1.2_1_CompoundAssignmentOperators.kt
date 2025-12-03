package ch07.ex1_2_1_CompoundAssignmentOperators

/**
 * @author runningpig66
 * @date 2025/12/3 周三
 * @time 11:57
 * §7.1 重载算术运算符 §7.1.2 重载复合赋值运算符
 * P.183 代码清单
 *
 * 通常情况下，当你在定义像 plus 这样的运算符函数时，Kotlin 不止支持 + 号运算，也支持 +=.
 * 像 +=, -= 等这些运算符被称为复合赋值运算符。看下面这个例子：
 * 这等同于 point = point + Point(3, 4) 的写法。当然，这个只对于可变变量有效。
 */
data class Point(val x: Int, val y: Int)

operator fun Point.plus(other: Point): Point {
    return Point(x + other.x, y + other.y)
}

fun main() {
    // point 必须是可变引用 var
    var point = Point(1, 2)
    point += Point(3, 4)
    println(point)
}
/* Output:
Point(x=4, y=6)
 */
