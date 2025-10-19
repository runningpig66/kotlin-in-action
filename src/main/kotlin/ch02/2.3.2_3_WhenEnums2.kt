package ch02.ex3_2_3_WhenEnums

import ch02.colors.Color // 导入其他包中义的Color类
import ch02.colors.Color.* // 显式地导入枚举常量就可以使用它们的名称

/**
 * @author runningpig66
 * @date 2025/10/14 周二
 * @time 4:57
 * P.30 代码清单 2.14 导入枚举常量后不用限定词就可以访问
 */
fun getWarmth(color: Color) = when (color) {
    // 使用导入的常量的名称
    RED, ORANGE, YELLOW -> "warm"
    GREEN -> "neutral"
    BLUE, INDIGO, VIOLET -> "cold"
}

fun main() {
    println(getWarmth(ORANGE))
}
/* Output:
warm
 */
