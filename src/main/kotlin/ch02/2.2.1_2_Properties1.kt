package ch02.person

/**
 * @author runningpig66
 * @date 2025/10/14 周二
 * @time 3:00
 * P.24 代码清单 2.5 在类中声明可变属性
 * P.25 代码清单 2.7 在 Kotlin 中使用 Person 类
 */
class Person(val name: String, var isMarried: Boolean)

fun main() {
    val person = Person("Bob", true)
    println(person.name)
    println(person.isMarried)
    person.isMarried = false
}
/* Output:
Bob
true
 */
