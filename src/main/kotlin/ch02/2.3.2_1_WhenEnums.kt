package ch02.ex3_2_1_WhenEnums

/**
 * @author runningpig66
 * @date 2025/10/14 周二
 * @time 4:31
 * P.29 代码清单 2.12 使用 when 来选择正确的枚举值
 */
enum class Color {
    RED, ORANGE, YELLOW, GREEN, BLUE, INDIGO, VIOLET
}

fun getMnemonic(color: Color) = // 直接返回一个 “when” 表达
    when (color) { // 如果颜色和枚举常量相等就返回对应的字符串
        Color.RED -> "Richard"
        Color.ORANGE -> "Of"
        Color.YELLOW -> "York"
        Color.GREEN -> "Gave"
        Color.BLUE -> "Battle"
        Color.INDIGO -> "In"
        Color.VIOLET -> "Vain"
    }

fun main() {
    println(getMnemonic(Color.BLUE))
}
/* Output:
Battle
 */
