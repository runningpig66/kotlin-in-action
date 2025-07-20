package ch06.ex1_4_1_ElvisOperator

// 代码清单 6.4 使用 Elvis 运算符处理 null 值
fun strLenSafe(s: String?): Int = s?.length ?: 0

fun main() {
    println(strLenSafe("abc"))
    println(strLenSafe(null))
}
/** Output:
 * 3
 * 0
 */