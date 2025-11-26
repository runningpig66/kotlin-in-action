package ch05.ex1_3_4_SyntaxForLambdaExpressions3

/**
 * @author runningpig66
 * @date 2025/11/25 周二
 * @time 18:16
 * §5.1 Lambda 表达式和成员引用 §5.1.3 Lambda 表达式的语法
 * P.110 代码清单 5.6 把 lambda 作为命名实参传递
 * P.110 代码清单 5.7 把 lambda 放在括号外传递
 * P.110 代码清单 5.8 省略 lambda 参数类型
 * P.111 代码清单 5.9 使用默认参数名称
 *
 * public fun <T> Iterable<T>.joinToString(
 *     separator: CharSequence = ", ",
 *     prefix: CharSequence = "",
 *     postfix: CharSequence = "",
 *     limit: Int = -1,
 *     truncated: CharSequence = "...",
 *     transform: ((T) -> CharSequence)? = null
 * ): String
 */
data class Person(val name: String, val age: Int)

fun main() {
    val people = listOf(Person("Alice", 29), Person("Bob", 31))
    // 把 lambda 作为命名实参传递
    val names = people.joinToString(
        separator = " ",
        transform = { p: Person -> p.name }
    )
    println(names)

    // 把 lambda 放在括号外传递
    people.joinToString(separator = " ") { p: Person -> p.name }

    // 省略 lambda 参数类型
    people.maxBy { p: Person -> p.age }  // 显式地写出参数类型
    people.maxBy { p -> p.age } // 省略 lambda 参数类型：推导出参数类型

    // 使用默认参数名称 it 代替命名参数。如果当前上下文期望的是只有一个参数的 lambda
    // 且这个参数的类型可以推断出来，就会生成这个名称。
    people.maxBy { it.age } // "it" 是自动生成的参数名称

    // 如果你用变量存储 lambda, 那么就没有可以推断出参数类型的上下文，所以你必须显式地指定参数类型：
    val getAge: (Person) -> Int = { p: Person -> p.age }
    people.maxBy(getAge)

    // 迄今为止，你看到的例子都是由单个表达式或语句构成的 lambda.
    // 但是 lambda 并没有被限制在这样小的规模，它可以包含更多的语句。
    // 下面这种情况，最后一个表达式就是（lambda 的）结果：
    val sum = { x: Int, y: Int ->
        println("Computing the sum of $x and $y...")
        x + y
    }
    println(sum(1, 2))
}
/* Output:
Alice Bob
Computing the sum of 1 and 2...
3
 */
