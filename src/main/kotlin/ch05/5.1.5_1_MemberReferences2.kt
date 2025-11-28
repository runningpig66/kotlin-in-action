package ch05.ex1_5_1_MemberReferences2

/**
 * @author runningpig66
 * @date 2025/11/28 周五
 * @time 0:01
 * §5.1 Lambda 表达式和成员引用 §5.1.5 成员引用
 * P.115 代码清单 顶层函数引用
 */
data class Person(val name: String, val email: String)

fun sendEmail(person: Person, message: String) {
    println("Sending email to ${person.email} with message: $message")
}

fun main() {
    // lambda 委托给一个接收多个参数的函数 sendEmail(), 普通 lambda 写法
    val action: (Person, String) -> Unit = { person: Person, message: String ->
        sendEmail(person, message) // 这个 lambda 委托给 sendEmail 函数
    }
    // 简洁的函数引用写法
    val nextAction: (Person, String) -> Unit = ::sendEmail // 可以用成员引用代替
    // 调用两种方式
    val person = Person("Naoki", "naoki@example.com")
    action(person, "Hello from lambda!")
    nextAction(person, "Hello from function reference!")
}
/* Output:
Sending email to naoki@example.com with message: Hello from lambda!
Sending email to naoki@example.com with message: Hello from function reference!
 */
