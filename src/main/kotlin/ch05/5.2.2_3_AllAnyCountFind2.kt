package ch05.ex2_2_3_AllAnyCountFind2

// 代码清单 P.119
data class Person(val name: String, val age: Int)

val canBeInClub27 = { p: Person -> p.age <= 27 }

fun main() {
    val people = listOf(Person("Alice", 27), Person("Bob", 31))
    // find & firstOrNull: 返回第一个满足条件的元素，若没有则返回 null
    println(people.find(canBeInClub27))
    println(people.firstOrNull(canBeInClub27))
}
/** Output:
 * Person(name=Alice, age=27)
 * Person(name=Alice, age=27)
 */