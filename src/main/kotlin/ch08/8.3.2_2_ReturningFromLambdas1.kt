package ch08.ex3_2_2_ReturningFromLambdas1

/**
 * @author runningpig66
 * @date 2025/12/22 周一
 * @time 22:48
 * §8.3 高阶函数中的控制流 §8.3.2 从 lambda 返回：使用标签返回
 * P.227 代码清单 8.21 用函数名作为 return 标签
 *
 * 另外一种选择是，使用 lambda 作为参数的函数的函数名可以作为标签。
 *
 * 如果你显式地指定了 lambda 表达式的标签，再使用函数名作为标签没有任何效果。一个 lambda 表达式的标签数量不能多于一个。
 */
data class Person(val name: String, val age: Int)

val people: List<Person> = listOf(Person("Alice", 29), Person("Bob", 31))

fun lookForAlice(people: List<Person>) {
    people.forEach {
        if (it.name == "Alice") {
            // return@forEach 从 lambda 表达式返回
            return@forEach
        }
    }
    println("Alice might be somewhere")
}

fun main() {
    lookForAlice(people)
}
/* Output:
Alice might be somewhere
 */
