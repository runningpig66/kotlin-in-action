package ch05.ex3_1_1_ExecutingSequenceOperations

/**
 * @author runningpig66
 * @date 2025/11/28 周五
 * @time 16:55
 * §5.3 情性集合操作：序列 §5.3.1 执行序列操作：中间和未端操作
 * P.123 - 125 代码清单
 *
 * 序列操作分为两类：中间的和末端的。一次中间操作返回的是另一个序列，这个新序列知道如何变换原始序列中的元素。
 * 而一次末端操作返回的是一个结果，这个结果可能是集合、元素、数字，或者其他从初始集合的变换序列中获取的任意对象。
 *
 * TODO 各种方法摘录
 */
fun main() {
    // 中间操作始终都是情性的。执行这段代码并不会在控制台上输出任何内容。
    // 这意味着 map 和 filter 变换被延期了，它们只有在获取结果的时候才会被应用（即末端操作被调用的时候）
    listOf(1, 2, 3, 4).asSequence()
        .map { print("map($it) "); it * it }
        .filter { print("filter($it) "); it % 2 == 0 }

    // 末端操作触发执行了所有的延期计算。
    listOf(1, 2, 3, 4).asSequence()
        .map { print("map($it) "); it * it }
        .filter { print("filter($it) "); it % 2 == 0 }
        .toList()
    println()

    // 及早求值在整个集合上执行每个操作；情性求值则逐个处理元素
    println(
        listOf(1, 2, 3, 4).asSequence()
            .map { it * it }
            .find { it > 3 }
    )

    // 在集合上执行操作的顺序也会影响性能
    data class Person(val name: String, val age: Int)

    val people = listOf(
        Person("Alice", 29),
        Person("Bob", 31),
        Person("Charles", 31),
        Person("Dan", 21)
    )
    // 先 "map" 后 "filter"
    println(
        people.asSequence()
            .map(Person::name)
            .filter { it.length < 4 }
            .toList()
    )
    // 先 "filter" 后 "map"
    // 先应用 filter 有助于减少变换的总次数：
    // 如果 map 在前，每个元素都被变换。而如果 filter 在前，不合适的元素会被尽早地过滤掉且不会发生变换。
    println(
        people.asSequence()
            .filter { it.name.length < 4 }
            .map(Person::name)
            .toList()
    )
}
/* Output:
map(1) filter(1) map(2) filter(4) map(3) filter(9) map(4) filter(16)
4
[Bob, Dan]
[Bob, Dan]
 */
