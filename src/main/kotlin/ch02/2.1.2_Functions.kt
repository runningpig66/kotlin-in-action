package ch02.ex1_2_Functions

/**
 * @author runningpig66
 * @date 2025/10/13 周一
 * @time 12:20
 * P.18 ~ P.20 函数
 */
fun max(a: Int, b: Int): Int {
    return if (a > b) a else b
}

fun max2(a: Int, b: Int): Int = if (a > b) a else b

fun max3(a: Int, b: Int) = if (a > b) a else b

fun main() {
    println(max(1, 2))
}
/* Output:
2
 */
