package ch02.ex3_1_DeclaringEnumClasses

/**
 * @author runningpig66
 * @date 2025/10/14 周二
 * @time 4:22
 * P.28 代码清单 2.11 声明一个带属性的枚举类
 */
enum class Color(val r: Int, val g: Int, val b: Int) { // 声明枚举常量的属性
    // 在每个常量创建的时候指定属性值
    RED(255, 0, 0),
    ORANGE(255, 165, 0),
    YELLOW(255, 255, 0),
    GREEN(0, 255, 0),
    BLUE(0, 0, 255),
    INDIGO(75, 0, 130),
    VIOLET(238, 130, 238); // 这里必须要有分号
    // 如果要在枚举类中定义任何方法，就要使用分号把枚举常量列表和方法定义分开。

    // 给枚举类定义一个方法
    fun rgb() = (r * 256 + g) * 256 + b
}

fun main() {
    println(Color.BLUE.rgb())
}
/* Output:
255
 */
