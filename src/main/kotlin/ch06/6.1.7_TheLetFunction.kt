package ch06.ex1_7_TheLetFunction

/**
 * @author runningpig66
 * @date 2025/12/1 周一
 * @time 16:00
 * §6.1 可空性 §6.1.7 "let" 函数
 * P.149 代码清单 6.9 使用 let 调用一个接受非空参数的函数
 *
 * let 函数让处理可空表达式变得更容易。let 函数把一个调用它的对象变成 lambda 表达式的参数。
 * 如果结合安全调用语法，它能有效地把调用 let 函数的可空对象，转变成非空类型。
 *
 * public inline fun <T, R> T.let(block: (T) -> R): R
 */
fun sendEmailTo(email: String) {
    println("Sending email to $email")
}

fun main() {
    var email: String? = "yole@example.com"
    email?.let { sendEmailTo(it) }
    email = null
    email?.let { sendEmailTo(it) }
}
/* Output:
Sending email to yole@example.com
 */
