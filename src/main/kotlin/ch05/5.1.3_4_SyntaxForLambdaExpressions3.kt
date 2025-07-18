package ch05.ex1_3_4_SyntaxForLambdaExpressions3

// 代码清单 5.6 把 lambda 作为命名实参传递
// 代码清单 5.7 把 lambda 放在括号外传递
// 代码清单 5.8 省略 lambda 参数类型
// 代码清单 5.9 使用默认参数名称
data class Person(val name: String, val age: Int)

fun main() {
    val people = listOf(Person("Alice", 29), Person("Bob", 31))
    val names = people.joinToString(
        separator = " ",
        transform = { p: Person -> p.name })
    println(names)

    people.joinToString(separator = " ") { p: Person -> p.name }

    people.maxBy { p: Person -> p.age }
    people.maxBy { p -> p.age }
    people.maxBy { it.age }
}
/** Output:
 * Alice Bob
 */