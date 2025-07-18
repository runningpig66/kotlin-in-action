package ch05.ex2_1_2_FilterMap1

// 代码清单 P.116
data class Person(val name: String, val age: Int)

fun main() {
    val people = listOf(Person("Alice", 29), Person("Bob", 31))
    println(people.filter { it.age > 30 })
}
/**
 * [Person(name=Bob, age=31)]
 */