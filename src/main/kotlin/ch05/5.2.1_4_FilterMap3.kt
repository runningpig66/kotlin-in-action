package ch05.ex2_1_4_FilterMap3

// 代码清单 P.117
data class Person(val name: String, val age: Int)

fun main() {
    val people = listOf(Person("Alice", 29), Person("Bob", 31))
    println(people.map { it.name })
    println(people.map(Person::name))
    // 打印年龄超过 30 岁的人的名字
    println(people.filter { it.age > 30 }.map { it.name })
    // 打印所有年龄最大的人的名字1
    println(people.filter { it.age == people.maxBy(Person::age).age })
    // 打印所有年龄最大的人的名字2
    val maxAge = people.maxBy(Person::age).age
    println(people.filter { it.age == maxAge })
}
/** Output:
 * [Alice, Bob]
 * [Alice, Bob]
 * [Bob]
 * [Person(name=Bob, age=31)]
 * [Person(name=Bob, age=31)]
 */