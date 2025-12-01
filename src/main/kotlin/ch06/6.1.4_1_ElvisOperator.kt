package ch06.ex1_4_1_ElvisOperator

import ch06.ex1_3_3_SafeCallOperator2.Person

/**
 * @author runningpig66
 * @date 2025/12/1 周一
 * @time 11:49
 * §6.1 可空性 §6.1.4 Elvis 运算符："?:"
 * P.143 代码清单 6.4 使用 Elvis 运算符处理 null 值
 */
fun strLenSafe(s: String?): Int = s?.length ?: 0

// 代码清单 6.3 中的函数 countryName 现在也可以使用一行代码完成。
fun Person.countryName2() = company?.address?.country ?: "Unknown"

fun main() {
    println(strLenSafe("abc"))
    println(strLenSafe(null))

    val person = Person("Dmitry", null)
    println(person.countryName2())
}
/* Output
3
0
Unknown
 */
