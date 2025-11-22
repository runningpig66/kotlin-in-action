package ch04.ex2_3_1_ImplementingPropertiesDeclaredInInterfaces

/**
 * @author runningpig66
 * @date 2025/11/22 周六
 * @time 22:58
 * §4.2 声明一个带非默认构造方法或属性的类 §4.2.3 实现在接口中声明的属性
 * P.85 代码清单 4.14 实现一个接口属性
 *
 * 在 Kotlin 中，接口可以包含抽象属性声明。这意味着实现 User 接口的类需要提供一个取得 nickname 值的方式。
 * 接口并没有说明这个值应该存储到一个支持字段还是通过 getter 来获取。
 * 接口本身并不包含任何状态，因此只有实现这个接口的类在需要的情况下会存储这个值。
 */
fun getFacebookName(accountId: Int) = "fb:$accountId"

interface User {
    val nickname: String
}

// 主构造方法属性
class PrivateUser(override val nickname: String) : User

class SubscribingUser(val email: String) : User {
    // 自定义 getter：这个属性没有一个支持字段来存储它的值，它只有一个 getter 在每次调用时从 email 中得到昵称。
    override val nickname: String
        get() = email.substringBefore('@')
}

class FacebookUser(val accountId: Int) : User {
    // 属性初始化：属性有一个支持字段来存储在类初始化时计算得到的数据。只在类初始化时调用一次。
    override val nickname: String = getFacebookName(accountId)
}

fun main() {
    println(PrivateUser("test@kotlinlang.org").nickname)
    println(SubscribingUser("test@kotlinlang.org").nickname)
}
/* Output:
test@kotlinlang.org
test
 */
