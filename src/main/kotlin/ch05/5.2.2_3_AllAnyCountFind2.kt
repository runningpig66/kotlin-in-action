package ch05.ex2_2_3_AllAnyCountFind2

/**
 * @author runningpig66
 * @date 2025/11/28 周五
 * @time 11:33
 * §5.2 集合的函数式 API §5.2.2 "all" "any" "count" 和 "find": 对集合应用判断式
 * P.119 代码清单 count
 *
 * public inline fun <T> Iterable<T>.count(predicate: (T) -> Boolean): Int
 */
data class Person(val name: String, val age: Int)

val canBeInClub27 = { p: Person -> p.age <= 27 }
fun main() {
    // 如果你想知道有多少个元素满足了判断式，使用 count:
    val people = listOf(Person("Alice", 27), Person("Bob", 31))
    // count: 统计满足条件的元素个数
    println(people.count(canBeInClub27))

    // 使用正确的函数完成工作："count" VS. "size"
    // count 方法容易被遗忘，然后通过过滤集合之后再取大小来实现它：
    println(people.filter(canBeInClub27).size)
    // 在这种情况下，一个中间集合会被创建并用来存储所有满足判断式的元素。
    // 而另一方面，count 方法只是跟踪匹配元素的数量，不关心元素本身，所以更高效。
}
/* Output:
1
1
 */
