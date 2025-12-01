package ch06.ex1_3_1_SafeCallOperator

/**
 * @author runningpig66
 * @date 2025/12/1 周一
 * @time 11:22
 * §6.1 可空性 §6.1.3 安全调用运算符："?."
 * P.142 代码清单
 */
fun printAllCaps(s: String?) {
    val allCaps: String? = s?.uppercase() // allCaps 是可空类型的 String?
    println(allCaps)
}

fun main() {
    printAllCaps("abc")
    println(null)
}
/* Output:
ABC
null
 */
