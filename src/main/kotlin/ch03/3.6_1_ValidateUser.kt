package ch03.ValidateUser

/**
 * @author runningpig66
 * @date 2025/11/22 周六
 * @time 0:57
 * §3.6 让你的代码更整洁：局部函数和扩展
 * P.66 代码清单 3.11 带重复代码的函数
 */
class User(val id: Int, val name: String, val address: String)

fun saveUser(user: User) {
    if (user.name.isEmpty()) {
        throw IllegalArgumentException("Can't save user ${user.id}: empty Name")
    }
    if (user.address.isEmpty()) {
        throw IllegalArgumentException("Can't save user ${user.id}: empty Address")
    }
    // Save user to the database
}

fun main() {
    saveUser(User(1, "", ""))
}
/* Output:
Exception in thread "main" java.lang.IllegalArgumentException: Can't save user 1: empty Name
	at ch03.ValidateUser._3_6_1_ValidateUserKt.saveUser(3.6_1_ValidateUser.kt:15)
	at ch03.ValidateUser._3_6_1_ValidateUserKt.main(3.6_1_ValidateUser.kt:24)
	at ch03.ValidateUser._3_6_1_ValidateUserKt.main(3.6_1_ValidateUser.kt)
 */
