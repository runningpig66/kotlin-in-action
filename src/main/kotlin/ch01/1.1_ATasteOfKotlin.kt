package ch01.ex1_ATasteOfKotlin

/**
 * @author runningpig66
 * @date 2025/10/13 周一
 * @time 10:53
 * P.4 代码清单 1.1 Kotlin 初体验
 */
data class Person(val name: String, val age: Int? = null)

fun main(args: Array<String>) {
    val persons = listOf(
        Person("Alick"),
        Person("Bob", age = 29),
    )

    val oldest = persons.maxBy { it.age ?: 0 }
    println("The oldest is: $oldest")
}
/* Output:
The oldest is: Person(name=Bob, age=29)
 */
