package geometry.shapes

import java.util.*

/**
 * @author runningpig66
 * @date 2025/10/14 周二
 * @time 3:53
 * P.26 代码清单 2.8 把类和函数的声明放在包中
 */
class Rectangle(val height: Int, val width: Int) {
    val isSquare: Boolean
        get() = height == width
}

fun createRandomRectangle(): Rectangle {
    val random = Random()
    return Rectangle(random.nextInt(), random.nextInt())
}
