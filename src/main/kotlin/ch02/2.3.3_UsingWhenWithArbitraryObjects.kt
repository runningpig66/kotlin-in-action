package ch02.ex3_3_UsingWhenWithArbitraryObjects

import ch02.colors.Color
import ch02.colors.Color.*

/**
 * @author runningpig66
 * @date 2025/10/14 周二
 * @time 5:03
 * P.30 代码清单 2.15 在 when 分支中使用不同的对象
 */
fun mix(c1: Color, c2: Color) =
    // “when” 表达式的实参可以是任何对象，它被检查是否与分支条件相等
    when (setOf(c1, c2)) {
        // 列举出能够混合的颜色对
        setOf(RED, YELLOW) -> ORANGE
        setOf(YELLOW, BLUE) -> GREEN
        setOf(BLUE, VIOLET) -> INDIGO
        // 如果没有任何其他分支匹配这里就会执行
        else -> throw Exception("Dirty color")
    }

fun main() {
    println(mix(BLUE, YELLOW))
}
/* Output:
GREEN
 */
