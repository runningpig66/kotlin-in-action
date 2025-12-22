package ch08.LookForAlice

/**
 * @author runningpig66
 * @date 2025/12/22 周一
 * @time 22:13
 * §8.3 高阶函数中的控制流 §8.3.1 lambda 中的返回语句：从一个封闭的函数返回
 * P.225 代码清单 8.18 在一个普通循环中使用 return
 *
 * 来比较两种不同的遍历集合的方法。在下面的代码清单中，很明显如果一个人的名字是 Alice，就应该从函数 lookForAlice 返回。
 */
data class Person(val name: String, val age: Int)

val people: List<Person> = listOf(Person("Alice", 29), Person("Bob", 31))

fun lookForAlice(people: List<Person>) {
    for (person in people) {
        if (person.name == "Alice") {
            println("Found!")
            return
        }
    }
    // 如果 "people" 中没有 Alice, 这一行就会被打印出来
    println("Alice is not found")
}

fun main() {
    lookForAlice(people)
}
/* Output:
Found!
 */
