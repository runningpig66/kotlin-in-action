package ch06.ex1_1_NullableTypes

// 代码清单 6.1 使用 if 检查处理 null
fun strLenSafe(s: String?): Int =
    if (s != null) s.length else 0

fun main() {
    val x: String? = null
    println(strLenSafe(x))
    println(strLenSafe("abc"))
}
/** Output:
 * 0
 * 3
 */