package ch06.ex1_1_NullableTypes

/**
 * @author runningpig66
 * @date 2025/12/1 周一
 * @time 10:59
 * §6.1 可空性 §6.1.1 可空类型
 * P.139 代码清单 6.1 使用 if 检查处理 null
 */
fun strLenSafe(s: String?): Int =
    if (s != null) {
        s.length // 增加了 null 检查后，这段代码就可以编译了
    } else {
        0
    }

fun main() {
    val x: String? = null
    println(strLenSafe(x))
    println(strLenSafe("abc"))
}
/* Output:
0
3
 */
