package ch02.ex2_2_CustomAccessors

/**
 * @author runningpig66
 * @date 2025/10/14 周二
 * @time 3:37
 * P.25 自定义访问器
 */
class Rectangle(val height: Int, val width: Int) {
    val isSquare: Boolean
        get() {
            return height == width
        }
}

fun main() {
    val rectangle = Rectangle(41, 43)
    println(rectangle.isSquare)
}
/* Output:
false
 */
