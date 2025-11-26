package ch05.ex1_2_LambdasAndCollections

/**
 * @author runningpig66
 * @date 2025/11/25 周二
 * @time 16:37
 * §5.1 Lambda 表达式和成员引用 §5.1.2 Lambda 和集合
 * P.107 代码清单 5.3 手动在集合中搜索
 * P.107 代码清单 5.4 用 lambda 在集合中搜索
 * P.108 代码清单 5.5 用成员引用搜
 *
 * 假设现在你有一个人的列表，需要找到列表中年龄最大的那个人。如果完全不了解 lambda, 你可能会急急忙忙地手动实现这个搜索功能。
 */
data class Person(val name: String, val age: Int)

fun findTheOldest(people: List<Person>) {
    // 存储最大年龄
    var maxAge = 0
    // 存储年龄最大的人
    var theOldest: Person? = null
    for (person in people) {
        // 如果下一个人比现在年龄最大的人还要大，改变最大值
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
    // 比较年龄找到最大的元素
    println(people.maxBy { it.age })
    // 如果 lambda 刚好是函数或者属性的委托，可以用成员引用替换。
    println(people.maxBy(Person::age))
}
/* Output:
Person(name=Bob, age=31)
Person(name=Bob, age=31)
Person(name=Bob, age=31)
 */
