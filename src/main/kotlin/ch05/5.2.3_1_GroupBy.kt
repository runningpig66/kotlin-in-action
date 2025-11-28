package ch05.ex2_3_1_GroupBy

/**
 * @author runningpig66
 * @date 2025/11/28 周五
 * @time 12:11
 * §5.2 集合的函数式 API §5.2.3 groupBy: 把列表转换成分组的 map
 * P.119 代码清单
 *
 * public inline fun <T, K> Iterable<T>.groupBy(keySelector: (T) -> K): Map<K, List<T>>
 */
data class Person(val name: String, val age: Int)

fun main() {
    val people: List<Person> = listOf(Person("Alice", 31), Person("Bob", 29), Person("Carol", 31))
    // groupBy: 按指定规则对元素分组，返回 Map<键, List<值>>
    // 每一个分组都是存储在一个列表中，结果的类型就是 Map<Int，List<Person>>.
    // 可以使用像 mapKeys 和 mapValues 这样的函数对这个 map 做进一步的修改。
    val ageGroup: Map<Int, List<Person>> = people.groupBy { it.age }
    val ageGroup1: Map<Int, List<Person>> = people.groupBy(Person::age)
    println(ageGroup)
    println(ageGroup1)
}
/* Output:
{31=[Person(name=Alice, age=31), Person(name=Carol, age=31)], 29=[Person(name=Bob, age=29)]}
{31=[Person(name=Alice, age=31), Person(name=Carol, age=31)], 29=[Person(name=Bob, age=29)]}
 */
