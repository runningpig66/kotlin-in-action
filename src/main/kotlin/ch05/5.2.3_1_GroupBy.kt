package ch05.ex2_3_1_GroupBy

// 代码清单 P.119
data class Person(val name: String, val age: Int)

fun main() {
    val people = listOf(Person("Alice", 31), Person("Bob", 29), Person("Carol", 31))
    // groupBy: 按指定规则对元素分组，返回 Map<键, List<值>>
    println(people.groupBy { it.age })
}
/** Output:
 * {31=[Person(name=Alice, age=31), Person(name=Carol, age=31)], 29=[Person(name=Bob, age=29)]}
 */