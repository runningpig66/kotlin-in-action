package ch06.ex1_3_1_SafeCallOperator

// 代码清单 P.142
fun printAllCaps(s: String?) {
    val allCaps: String? = s?.uppercase()
    println(allCaps)
}

fun main() {
    printAllCaps("abc")
    println(null)
}
/** Output:
 * ABC
 * null
 */