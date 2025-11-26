package ch05.ex1_4_1_AccessingVariablesInScope

/**
 * @author runningpig66
 * @date 2025/11/25 周二
 * @time 20:35
 * §5.1 Lambda 表达式和成员引用 §5.1.4 在作用域中访问变量
 * P.112 代码清单 5.10 在 lambda 中使用函数参数
 *
 * 当在函数内声明一个匿名内部类的时候，能够在这个匿名类内部引这个函数的参数和局部变量。也可以用 lambda 做同样的事情。
 * 如果在函数内部使用 lambda 也可以访间这个函数的参数，还有在 lambda 之前定义的局部变量。
 */
fun printMessagesWithPrefix(messages: Collection<String>, prefix: String) {
    // 接收 lambda 作为实参，指定对每个元素的操作
    messages.forEach {
        // 在 lambda 中访问 "prefix" 参数
        println("$prefix $it")
    }
}

fun main() {
    val errors = listOf("403 Forbidden", "404 Not Found")
    printMessagesWithPrefix(errors, "Error:")
}
/* Output:
Error: 403 Forbidden
Error: 404 Not Found
 */
