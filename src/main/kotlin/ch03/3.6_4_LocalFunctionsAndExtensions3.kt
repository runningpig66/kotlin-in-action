package ch03.ex6_4_LocalFunctionsAndExtensions3

/**
 * @author runningpig66
 * @date 2025/11/22 周六
 * @time 1:36
 * §3.6 让你的代码更整洁：局部函数和扩展
 * P.67 代码清单 3.14 提取逻辑到扩展函数
 *
 * 我们可以继续改进，把验证逻辑放到 User 类的扩展函数中。
 */
class User(val id: Int, val name: String, val address: String)

fun User.validateBeforeSave() {
    fun validate(value: String, fieldName: String) {
        if (value.isEmpty()) {
            // 可以直接访问 User 的属性
            throw IllegalArgumentException("Can't save user $id: empty $fieldName")
        }
    }
    validate(this.name, "Name")
    validate(address, "Address")
}

fun saveUser(user: User) {
    // 调用扩展函数
    user.validateBeforeSave()
    // Save user to the database
}

fun main() {
    saveUser(User(1, "", ""))
}
/* Output:
Exception in thread "main" java.lang.IllegalArgumentException: Can't save user 1: empty Name
	at ch03.ex6_4_LocalFunctionsAndExtensions3._3_6_4_LocalFunctionsAndExtensions3Kt.validateBeforeSave$validate(3.6_4_LocalFunctionsAndExtensions3.kt:17)
	at ch03.ex6_4_LocalFunctionsAndExtensions3._3_6_4_LocalFunctionsAndExtensions3Kt.validateBeforeSave(3.6_4_LocalFunctionsAndExtensions3.kt:20)
	at ch03.ex6_4_LocalFunctionsAndExtensions3._3_6_4_LocalFunctionsAndExtensions3Kt.saveUser(3.6_4_LocalFunctionsAndExtensions3.kt:25)
	at ch03.ex6_4_LocalFunctionsAndExtensions3._3_6_4_LocalFunctionsAndExtensions3Kt.main(3.6_4_LocalFunctionsAndExtensions3.kt:30)
	at ch03.ex6_4_LocalFunctionsAndExtensions3._3_6_4_LocalFunctionsAndExtensions3Kt.main(3.6_4_LocalFunctionsAndExtensions3.kt)
 */
