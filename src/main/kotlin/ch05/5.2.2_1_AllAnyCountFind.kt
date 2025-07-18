package ch05.ex2_2_1_AllAnyCountFind

// 代码清单 P.118
// 代码清单 P.119
data class Person(val name: String, val age: Int)

val canBeInClub27 = { p: Person -> p.age <= 27 }

fun main() {
    val people = listOf(Person("Alice", 27), Person("Bob", 31))
    // all: 判断集合中所有元素是否都满足条件
    println(people.all(canBeInClub27))
    // any: 判断集合中是否存在至少一个满足条件的元素
    println(people.any(canBeInClub27))
    // count: 统计满足条件的元素个数
    println(people.count(canBeInClub27))
}
/** Output:
 * false
 * true
 * 1
 */