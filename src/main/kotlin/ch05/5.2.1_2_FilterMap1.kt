package ch05.ex2_1_2_FilterMap1

/**
 * @author runningpig66
 * @date 2025/11/28 周五
 * @time 1:10
 * §5.2 集合的函数式 API §5.2.1 基础：filter 和 map
 * P.116 代码清单 filter
 *
 * 如果你想留下那些超过 30 岁的人，可以用 filter:
 *
 * public inline fun <T> Iterable<T>.filter(predicate: (T) -> Boolean): List<T>
 */
data class Person(val name: String, val age: Int)

fun main() {
    val people = listOf(Person("Alice", 29), Person("Bob", 31))
    println(people.filter { it.age > 30 })
}
/* Output:
[Person(name=Bob, age=31)]
 */
