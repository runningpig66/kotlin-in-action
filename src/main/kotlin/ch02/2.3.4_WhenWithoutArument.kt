package ch02.ex3_4_WhenWithoutArument

import ch02.colors.Color
import ch02.colors.Color.*

/**
 * @author runningpig66
 * @date 2025/10/14 周二
 * @time 5:23
 * P.31 代码清单 2.16 不带参数的 when
 */
fun mixOptimized(c1: Color, c2: Color) =
    when { // 没有实参传给 “when”
        (c1 == RED && c2 == YELLOW) || (c1 == YELLOW && c2 == RED) -> ORANGE
        (c1 == YELLOW && c2 == BLUE) || (c1 == BLUE && c2 == YELLOW) -> GREEN
        (c1 == BLUE && c2 == VIOLET) || (c1 == VIOLET && c2 == BLUE) -> INDIGO
        else -> throw Exception("Dirty color")
    }

fun main() {
    println(mixOptimized(BLUE, YELLOW))
}
/* Output:
GREEN
 */
