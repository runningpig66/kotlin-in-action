package ch03.ex6_2_LocalFunctionsAndExtensions1

/**
 * @author runningpig66
 * @date 2025/11/22 周六
 * @time 1:20
 * §3.6 让你的代码更整洁：局部函数和扩展
 * P.66 代码清单 3.12 提取局部函数来避免重复
 *
 * 如果将验证代码放到局部函数中，可以脱重复，并保持清晰的代码结构，可以这样做：
 */
class User(val id: Int, val name: String, val address: String)

fun saveUser(user: User) {
    // 声明一个局部函数来验证所有字段
    fun validate(user: User, value: String, fieldName: String) {
        if (value.isEmpty()) {
            throw IllegalArgumentException("Can't save user ${user.id}: empty $fieldName")
        }
    }
    // 调用局部函数来验证特定字段
    validate(user, user.name, "Name")
    validate(user, user.address, "Address")
}

fun main() {
    saveUser(User(1, "", ""))
}
/* Output:
Exception in thread "main" java.lang.IllegalArgumentException: Can't save user 1: empty Name
	at ch03.ex6_2_LocalFunctionsAndExtensions1._3_6_2_LocalFunctionsAndExtensions1Kt.saveUser$validate(3.6_2_LocalFunctionsAndExtensions1.kt:16)
	at ch03.ex6_2_LocalFunctionsAndExtensions1._3_6_2_LocalFunctionsAndExtensions1Kt.saveUser(3.6_2_LocalFunctionsAndExtensions1.kt:19)
	at ch03.ex6_2_LocalFunctionsAndExtensions1._3_6_2_LocalFunctionsAndExtensions1Kt.main(3.6_2_LocalFunctionsAndExtensions1.kt:24)
	at ch03.ex6_2_LocalFunctionsAndExtensions1._3_6_2_LocalFunctionsAndExtensions1Kt.main(3.6_2_LocalFunctionsAndExtensions1.kt)
 */
