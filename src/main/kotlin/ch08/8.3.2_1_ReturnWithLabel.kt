package ch08.ReturnWithLabel

/**
 * @author runningpig66
 * @date 2025/12/22 周一
 * @time 22:36
 * §8.3 高阶函数中的控制流 §8.3.2 从 lambda 返回：使用标签返回
 * P.226 代码清单 8.20 用一个标签实现局部返回
 *
 * 也可以在 lambda 表达式中使用局部返回。lambda 中的局部返回跟 for 循环中的 break 表达式相似。
 * 它会终止 lambda 的执行，并接着从调用 lambda 的代码处执行。要区分局部返回和非局部返回，要用到标签。
 * 想从一个 lambda 表达式处返回你可以标记它，然后在 return 关键字后面引用这个标签。
 *
 * 要标记一个 lambda 表达式，在 lambda 的花括号之前放一个标签名（可以是任何标识符），接着放一个 @ 符号。
 * 要从一个 lambda 返回，在 return 关键字后放一个 @ 符号，接着放标签名。
 */
data class Person(val name: String, val age: Int)

val people: List<Person> = listOf(Person("Alice", 29), Person("Bob", 31))

fun lookForAlice(people: List<Person>) {
    // 给 lambda 表达式加上标签
    people.forEach label@{
        if (it.name == "Alice") {
            // return@label 引用了这个标签
            return@label
        }
    }
    // 这一行总是会被打印出来
    println("Alice might be somewhere")
}

fun main() {
    lookForAlice(people)
}
/* Output:
Alice might be somewhere
 */
