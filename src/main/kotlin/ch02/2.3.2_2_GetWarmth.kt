package ch02.GetWarmth

/**
 * @author runningpig66
 * @date 2025/10/14 周二
 * @time 4:50
 * P.30 代码清单 2.13 在一个 when 分支上合并多个选项
 */
enum class Color {
    RED, ORANGE, YELLOW, GREEN, BLUE, INDIGO, VIOLET
}

fun getWarmth(color: Color) =
    when (color) {
        Color.RED, Color.ORANGE, Color.YELLOW -> "warm"
        Color.GREEN -> "neutral"
        Color.BLUE, Color.INDIGO, Color.VIOLET -> "cold"
    }

fun main() {
    println(getWarmth(Color.ORANGE))
}
/* Output:
warm
 */
