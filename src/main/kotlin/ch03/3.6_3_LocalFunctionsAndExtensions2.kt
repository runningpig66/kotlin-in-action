package ch03.ex6_3_LocalFunctionsAndExtensions2

/**
 * @author runningpig66
 * @date 2025/11/22 周六
 * @time 1:29
 * §3.6 让你的代码更整洁：局部函数和扩展
 * P.67 代码清单 3.13 在局部函数中访问外层函数的参数
 *
 * 把 User 对象传递给验证函数看起来还是有点难看。好消息是，这完全不必要，
 * 因为局部函数可以访问所在函数中的所有参数和变量。我们可以利用这一点，去掉冗余的 User 参数。
 */
class User(val id: Int, val name: String, val address: String)

fun saveUser(user: User) {
    // 现在不用在 validate 函数中重复 user 参数了
    fun validate(value: String, fieldName: String) {
        if (value.isEmpty()) {
            // 可以直接访问外部函数的参数
            throw IllegalArgumentException("Can't save user ${user.id}: empty $fieldName")
        }
    }
    validate(user.name, "Name")
    validate(user.address, "Address")
    // Save user to the database
}

fun main() {
    saveUser(User(1, "", ""))
}
/* Output:
Exception in thread "main" java.lang.IllegalArgumentException: Can't save user 1: empty Name
	at ch03.ex6_3_LocalFunctionsAndExtensions2._3_6_3_LocalFunctionsAndExtensions2Kt.saveUser$validate(3.6_3_LocalFunctionsAndExtensions2.kt:15)
	at ch03.ex6_3_LocalFunctionsAndExtensions2._3_6_3_LocalFunctionsAndExtensions2Kt.saveUser(3.6_3_LocalFunctionsAndExtensions2.kt:18)
	at ch03.ex6_3_LocalFunctionsAndExtensions2._3_6_3_LocalFunctionsAndExtensions2Kt.main(3.6_3_LocalFunctionsAndExtensions2.kt:24)
	at ch03.ex6_3_LocalFunctionsAndExtensions2._3_6_3_LocalFunctionsAndExtensions2Kt.main(3.6_3_LocalFunctionsAndExtensions2.kt)
 */
