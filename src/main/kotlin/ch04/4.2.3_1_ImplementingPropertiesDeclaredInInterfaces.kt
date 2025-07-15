package ch04.ex2_3_1_ImplementingPropertiesDeclaredInInterfaces

// 代码清单 4.14 实现一个接口属性
fun getFacebookName(accountId: Int) = "fb:$accountId"

interface User {
    val nickname: String
}

class PrivateUser(override val nickname: String) : User

class SubscribingUser(val email: String) : User {
    override val nickname: String
        get() = email.substringBefore('@')
}

class FacebookUser(val accountId: Int) : User {
    // 初始化阶段调用一次，有幕后字段
    override val nickname: String = getFacebookName(accountId)
}

fun main() {
    println(PrivateUser("test@kotlinlang.org").nickname)
    println(SubscribingUser("test@kotlinlang.org").nickname)
}
/** Output:
 * test@kotlinlang.org
 * test
 */