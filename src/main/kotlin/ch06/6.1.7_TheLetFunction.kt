package ch06.ex1_7_TheLetFunction

// 代码清单 6.9 使用 let 调用一个接受非空参数的函数
fun sendEmailTo(email: String) {
    println("Sending email to $email")
}

fun main() {
    var email: String? = "runningpig66@gmail.com"
    email?.let { sendEmailTo(it) }
    email = null
    email?.let { sendEmailTo(it) }
}
/** Output:
 * Sending email to runningpig66@gmail.com
 */