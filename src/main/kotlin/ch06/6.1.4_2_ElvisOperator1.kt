package ch06.ex1_4_2_ElvisOperator1

// 代码清单 6.5 同时使用 throw 和 Elvis 运算符
class Address(
    val streetAddress: String, val zipCode: Int,
    val city: String, val country: String
)

class Company(val name: String, val address: Address?)
class Person(val name: String, val company: Company?)

fun printShippingLabel(person: Person) {
    val address: Address = person.company?.address ?: throw IllegalArgumentException("No address")
    with(address) {
        println(streetAddress)
        println("$zipCode $city, $country")
    }
}

fun main() {
    val address = Address("Elsestr. 47", 80687, "Munich", "Germany")
    val jetbrains = Company("JetBrains", address)
    val person = Person("Dmitry", jetbrains)
    printShippingLabel(person)
    printShippingLabel(Person("Alexey", null))
}
/** Output:
 * Elsestr. 47
 * 80687 Munich, Germany
 * Exception in thread "main" java.lang.IllegalArgumentException: No address...
 */