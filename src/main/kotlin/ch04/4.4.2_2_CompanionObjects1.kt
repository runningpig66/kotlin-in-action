package ch04.ex4_2_2_CompanionObjects1

// 代码清单 4.26 使用工厂方法来替代从构造方法
fun getFacebookName(accountId: Int) = "fb:$accountId"

class User private constructor(val nickname: String) {
    companion object {
        fun newSubscribingUser(email: String) =
            User(email.substringBefore('@'))

        fun newFacebookUser(accountId: Int) =
            User(getFacebookName(accountId))
    }
}

fun main() {
    val subscribingUser = User.newSubscribingUser("bob@gmail.com")
    val newFacebookUser = User.newFacebookUser(4)
    println(subscribingUser.nickname)
    println(newFacebookUser.nickname)
}
/** Output:
 * bob
 * fb:4
 */