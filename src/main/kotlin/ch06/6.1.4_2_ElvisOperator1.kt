package ch06.ex1_4_2_ElvisOperator1

/**
 * @author runningpig66
 * @date 2025/12/1 周一
 * @time 12:17
 * §6.1 可空性 §6.1.4 Elvis 运算符："?:"
 * P.144 代码清单 6.5 同时使用 throw 和 Elvis 运算符
 *
 * 在 Kotlin 中有种场景下 Elvis 运算符会特别顺手，像 return 和 throw 这样的操作其实是表达式，
 * 因此可以把它们写在 Elvis 运算符的右边。这种情况下，如果 Elvis 运算符左边的值为 null,
 * 函数就会立即返回一个值或者抛出一个异常。如果函数中需要检查先决条件，这个方式特别有用。
 */
class Address(val streetAddress: String, val zipCode: Int, val city: String, val country: String)
class Company(val name: String, val address: Address?)
class Person(val name: String, val company: Company?)

fun printShippingLabel(person: Person) {
    val address: Address = person.company?.address ?: throw IllegalArgumentException("") // 如果缺少 address 就抛出异常
    with(address) { // "address" 不为空
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
/* Output:
Elsestr. 47
Exception in thread "main" 80687 Munich, Germany
java.lang.IllegalArgumentException:
	at ch06.ex1_4_2_ElvisOperator1._6_1_4_2_ElvisOperator1Kt.printShippingLabel(6.1.4_2_ElvisOperator1.kt:19)
	at ch06.ex1_4_2_ElvisOperator1._6_1_4_2_ElvisOperator1Kt.main(6.1.4_2_ElvisOperator1.kt:31)
	at ch06.ex1_4_2_ElvisOperator1._6_1_4_2_ElvisOperator1Kt.main(6.1.4_2_ElvisOperator1.kt)
 */
