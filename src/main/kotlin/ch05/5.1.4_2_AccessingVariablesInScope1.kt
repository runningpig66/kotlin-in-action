package ch05.ex1_4_2_AccessingVariablesInScope1

/**
 * @author runningpig66
 * @date 2025/11/25 周二
 * @time 20:52
 * §5.1 Lambda 表达式和成员引用 §5.1.4 在作用域中访问变量
 * P.112 代码清单 5.11 在 lambda 中改变局部变量
 *
 * 和 Java 不一样，Kotlin 允许在 lambda 内部访问非 final 变量基至修改它们。
 * 从 lambda 内访问外部变量，我们称这些变量被 lambda 捕捉。
 *
 * 注意，默认情况下，局部变量的生命期被限制在声明这个变量的函数中。但是如果它被 lambda 捕捉了，
 * 使用这个变量的代码可以被存储并稍后再执行。你可能会问这是什么原理。当你捕捉 final 变量时，
 * 它的值和使用这个值的 lambda 代码一起存储。而对非 final 变量来说，它的值被封装在一个特殊的包装器中，
 * 这样你就可以改变这个值，而对这个包装器的引用会和 lambda 代码一起存储。
 *
 * 注意：书中的例子目前不适用，请见下方注释部分。
 */
fun printProblemCount(responses: Collection<String>) {
    var clientErrors = 0
    var serverErrors = 0
    responses.forEach {
        if (it.startsWith("4")) {
            clientErrors++
        } else if (it.startsWith("")) {
            serverErrors++
        }
    }
    println("$clientErrors client errors, $serverErrors server errors")

    // 注意：书中的例子目前不适用，请见下方注释部分。
    // forEach 是 inline 函数，所以无法通过该例子准确验证 lambda 中是否真的引用了局部变量
    // （如 clientErrors 和 serverErrors）的引用包装类型（Ref）。
    // 带有 inline 关键字的函数，其 lambda 参数在编译阶段就被展开为内联代码，不会生成真实的 Function 对象，
    // 也不会捕获闭包。因此：clientErrors++ 虽然是在 lambda 语法块中，但它其实就是普通代码块；
    // 编译器可以直接操作变量，不需要 Ref 包装；所以不会生成引用类型，也就看不到 Ref 的使用。
    // 如果想验证 Kotlin 是如何使用 Ref 封装变量引用，你需要使用一个非 inline 的 lambda 表达式，比如：
    fun makeCounter(): () -> Int {
        var counter = 0
        return { // 此处返回的是函数类型：(): Int
            counter++  // 这个 lambda 是闭包，捕获 counter 的引用
            counter
        }
    }

    val c1: () -> Int = makeCounter()
    val c11: Int = c1()
    println(c11)

    val c2 = makeCounter()
    println(c2())
    println(c2())
    println(c2())
}

fun main() {
    val response = listOf("200 OK", "418 I'm a teapot", "500 Internal Server Error")
    printProblemCount(response)
}
/* Output:
1 client errors, 2 server errors
1
1
2
3
 */
