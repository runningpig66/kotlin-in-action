package ch05.ex2_2_1_AllAnyCountFind

/**
 * @author runningpig66
 * @date 2025/11/28 周五
 * @time 11:09
 * §5.2 集合的函数式 API §5.2.2 "all" "any" "count" 和 "find": 对集合应用判断式
 * P.118 代码清单 all any
 *
 * public inline fun <T> Iterable<T>.all(predicate: (T) -> Boolean): Boolean
 * public inline fun <T> Iterable<T>.any(predicate: (T) -> Boolean): Boolean
 */
data class Person(val name: String, val age: Int)

val canBeInClub27 = { p: Person -> p.age <= 27 }

fun main() {
    val people = listOf(Person("Alice", 27), Person("Bob", 31))
    // all: 判断集合中所有元素是否都满足条件
    println(people.all(canBeInClub27))
    // any: 判断集合中是否存在至少一个满足条件的元素
    println(people.any(canBeInClub27))
}
/* Output:
false
true
 */
