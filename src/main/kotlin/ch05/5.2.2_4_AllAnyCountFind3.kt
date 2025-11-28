package ch05.ex2_2_4_AllAnyCountFind3

/**
 * @author runningpig66
 * @date 2025/11/28 周五
 * @time 11:33
 * §5.2 集合的函数式 API §5.2.2 "all" "any" "count" 和 "find": 对集合应用判断式
 * P.119 代码清单 find firstOrNull
 *
 * public inline fun <T> Iterable<T>.find(predicate: (T) -> Boolean): T?
 * public inline fun <T> Iterable<T>.firstOrNull(predicate: (T) -> Boolean): T?
 */
data class Person(val name: String, val age: Int)

val canBeInClub27 = { p: Person -> p.age <= 27 }

fun main() {
    val people = listOf(Person("Alice", 27), Person("Bob", 31))
    // find & firstOrNull: 返回第一个满足条件的元素，若没有则返回 null
    println(people.find(canBeInClub27))
    // find 还有一个同义方法 firstOrNull, 可以使用这个方法更清楚地表述你的意图。
    println(people.firstOrNull(canBeInClub27))
}
/* Output:
Person(name=Alice, age=27)
Person(name=Alice, age=27)
 */
