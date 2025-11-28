package ch05.ex2_1_4_FilterMap3

/**
 * @author runningpig66
 * @date 2025/11/28 周五
 * @time 1:25
 * §5.2 集合的函数式 API §5.2.1 基础：filter 和 map
 * P.117 代码清单 map
 *
 * 如果没有必要就不要重复计算！使用 lambda 表达式的代码看起来简单，有时候却掩盖了底层操作的复杂性。
 * 始终牢记你写的代码在干什么！！！
 */
data class Person(val name: String, val age: Int)

fun main() {
    val people = listOf(Person("Alice", 29), Person("Bob", 31))
    // 如果你想打印的只是一个姓名列表，而不是人的完整信息列表，可以用 map 来变换列表：
    println(people.map { it.name })
    // 注意，这个例子可以用成员引用漂亮地重写：
    println(people.map(Person::name))
    // 可以轻松地把多次这样的调用链接起来。例如，打印出年龄超过30岁的人的名字：
    println(people.filter { it.age > 30 }.map { it.name })
    // 现在，如果说需要这个分组中所有年龄最大的人的名字，可以先找到分组中人的最大年龄，
    // 然后返回所有这个年龄的人。很容易就用 lambda 写出如下代码：
    println(people.filter { it.age == people.maxBy(Person::age).age })
    // 但是请注意，这段代码对每个人都会重复寻找最大年龄的过程，假设集合中有 100 个人，
    // 寻找最大年龄的过程就会执行 100 遍！下面的解决方法做出了改进，只计算了一次最大年龄：
    val maxAge = people.maxBy(Person::age).age
    println(people.filter { it.age == maxAge })
}
/* Output:
[Alice, Bob]
[Alice, Bob]
[Bob]
[Person(name=Bob, age=31)]
[Person(name=Bob, age=31)]
 */
