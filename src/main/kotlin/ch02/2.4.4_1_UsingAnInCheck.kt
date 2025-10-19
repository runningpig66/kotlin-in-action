package ch02.ex4_4_1_UsingAnInCheck

/**
 * @author runningpig66
 * @date 2025/10/19 周日
 * @time 10:15
 * P.40 代码清单 2.25 使用 in 检查区间的成员
 */
fun isLetter(c: Char) = c in 'A'..'Z' || c in 'a'..'z' // 后者在底层变换成 a <= c && c <= z
fun isNotDigit(c: Char) = c !in '0'..'9'

fun main() {
    println(isLetter('q'))
    println(isNotDigit('x'))
}
/* Output:
true
true
 */
