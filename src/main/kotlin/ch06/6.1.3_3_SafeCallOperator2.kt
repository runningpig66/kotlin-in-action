package ch06.ex1_3_3_SafeCallOperator2

/**
 * @author runningpig66
 * @date 2025/12/1 周一
 * @time 11:36
 * §6.1 可空性 §6.1.3 安全调用运算符："?."
 * P.142 - 143 代码清单 6.3 链接多个安全调用
 *
 * 如果你的对象图中有多个可空类型的属性，通常可以在同一个表达式中方便地使用多个安全调用。
 */
class Address(val streetAddress: String, val zipCode: Int, val city: String, val country: String)
class Company(val name: String, val address: Address?)
class Person(val name: String, val company: Company?)

fun Person.countryName(): String {
    val country: String? = this.company?.address?.country // 多个安全调用链接在一起
    return if (country != null) country else "Unknown"
}

fun main() {
    val person = Person("Dmitry", null)
    println(person.countryName())
}
/* Output:
Unknown
 */
