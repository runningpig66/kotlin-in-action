package ch08.ex2_3_2_InliningCollectionOperations1

/**
 * @author runningpig66
 * @date 2025/12/21 周日
 * @time 22:05
 * §8.2 内联函数：消除 lambda 带来的运行时开销 §8.2.3 内联集合操作
 * P.222 代码清单
 *
 * 想象一下现在你连续调用 filter 和 map 两个操作。
 *
 * 这个例子使用了一个 lambda 表达式和一个成员引用。再一次，filter 和 map 函数都被声明为 inline 函数，
 * 所以它们的函数体会被内联，因此不会产生额外的类或者对象。但是下面的代码却创建了一个中间集合来保存列表过滤的结果，
 * 由 filter 函数生成的代码会向这个集合中添加元素，而由 map 函数生成的代码会读取这个集合。
 *
 * 如果有大量元素需要处理，中间集合的运行开销将成为不可忽视的问题，这时可以在调用链后加上一个 asSequence 调用，用序列来替代集合。
 * 但正如你在前一节中看到的，用来处理序列的 lambda 没有被内联。每一个中间序列被表示成把 lambda 保存在其字段中的对象，
 * 而末端操作会导致由每一个中间序列调用组成的调用链被执行。因此，即便序列上的操作是惰性的，
 * 你不应该总是试图在集合操作的调用链后加上 asSequence。这只在处理大量数据的集合时有用，小的集合可以用普通的集合操作处理。
 */
data class Person(val name: String, val age: Int)

val people = listOf(Person("Alice", 29), Person("Bob", 31))

fun main() {
    println(people.filter { it.age > 30 }.map(Person::name))
}
/* Output:
[Bob]
 */
