package ch06.ex1_3_3_SafeCallOperator2

// 代码清单 6.3 链接多个安全调用
class Address(
    val streetAddress: String, val zipCode: Int,
    val city: String, val country: String
)

class Company(val name: String, val address: Address?)
class Person(val name: String, val company: Company?)

//fun Person.countryName(): String {
//    val country = this.company?.address?.country
//    return if (country != null) country else "Unknown"
//}

fun Person.countryName() = company?.address?.country ?: "Unknown"

fun main() {
    val person = Person("Dmitry", null)
    println(person.countryName())
}
/** Output:
 * Unknown
 */