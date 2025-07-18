package ch05.ex1_2_LambdasAndCollections

// 代码清单 5.3 手动在集合中搜索
// 代码清单 5.4 用 lambda 在集合中搜索
// 代码清单 5.5 用成员引用搜索
data class Person(val name: String, val age: Int)

fun findTheOldest(people: List<Person>) {
    var maxAge = 0
    var theOldest: Person? = null
    for (person in people) {
        if (person.age > maxAge) {
            maxAge = person.age
            theOldest = person
        }
    }
    println(theOldest)
}

fun main() {
    val people = listOf(Person("Alice", 29), Person("Bob", 31))
    findTheOldest(people)
    println(people.maxBy { it.age })
    println(people.maxBy(Person::age))
}
/** Output:
 * Person(name=Bob, age=31)
 * Person(name=Bob, age=31)
 * Person(name=Bob, age=31)
 */