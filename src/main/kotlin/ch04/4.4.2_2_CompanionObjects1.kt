package ch04.ex4_2_2_CompanionObjects1

/**
 * @author runningpig66
 * @date 2025/11/24 周一
 * @time 9:33
 * §4.4 "object" 关键字：将声明一个类与创建一个实例结合起来 §4.4.2 伴生对象：工厂方法和静态成员的地盘
 * P.99 代码清单 4.26 使用工厂方法来替代从构造方
 *
 * 伴生对象可以访问类中的所有 private 成员，包括 private 构造方法，它是实现工厂模式的理想选择。
 * User 实例就是通过工厂方法来创建的，而不是通过多个构造方法。工厂方法能够返回声明这个方法的类的子类。
 *
 * notes: CompanionObjects.md
 * （伴生）对象字节码对比
 */

fun getFacebookName(accountId: Int): String = "fb:$accountId"

// 将主构造方法标记为私有
class User private constructor(val nickname: String) {
    // 声明伴生对象
    companion object {
        fun newSubscribingUser(email: String) = User(email.substringBefore('@'))

        // 用工厂方法通过 Facebook 账号来创建一个新用户
        fun newFacebookUser(accountId: Int) = User(getFacebookName(accountId))
    }
}

fun main() {
    // 可以通过类名来调用 companion object 的方法：
    val subscribingUser = User.newSubscribingUser("bob@gmail.com")
    val newFacebookUser = User.newFacebookUser(4)
    println(subscribingUser.nickname)
    println(newFacebookUser.nickname)
}
/* Output:
bob
fb:4
 */
