package ch05.ex1_5_1_MemberReferences2

// 代码清单 P.115
data class Person(val name: String, val email: String)

fun sendEmail(person: Person, message: String) {
    println("Sending email to ${person.email} with message: $message")
}

fun main() {
    // lambda 委托给一个接收多个参数的函数 sendEmail(), 普通 lambda 写法
    val action = { person: Person, message: String ->
        sendEmail(person, message)
    }
    // 简洁函数引用写法
    val nextAction: (Person, String) -> Unit = ::sendEmail
    // 调用两种方式
    val person = Person("Naoki", "naoki@example.com")
    action(person, "Hello from lambda!")
    nextAction(person, "Hello from function reference!")
}
/** Output:
 * Sending email to naoki@example.com with message: Hello from lambda!
 * Sending email to naoki@example.com with message: Hello from function reference!
 */