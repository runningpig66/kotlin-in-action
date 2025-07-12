package ch03.ex6_2_LocalFunctionsAndExtensions1

class User(val id: Int, val name: String, val address: String)

fun saveUser(user: User) {
    // 局部函数
    fun validate(user: User, value: String, fieldName: String) {
        if (value.isEmpty()) {
            throw IllegalArgumentException("Can't save user ${user.id}: empty $fieldName")
        }
    }

    validate(user, user.name, "Name")
    validate(user, user.address, "Address")

    // Save user to the database
}

fun main() {
    saveUser(User(1, "", ""))
}
/** Output:
 * Exception in thread "main" java.lang.IllegalArgumentException: Can't save user 1: empty Name
 * 	at ch03.ex6_2_LocalFunctionsAndExtensions1._3_6_2_LocalFunctionsAndExtensions1Kt.saveUser$validate(3.6_2_LocalFunctionsAndExtensions1.kt:9)
 * 	at ch03.ex6_2_LocalFunctionsAndExtensions1._3_6_2_LocalFunctionsAndExtensions1Kt.saveUser(3.6_2_LocalFunctionsAndExtensions1.kt:13)
 * 	at ch03.ex6_2_LocalFunctionsAndExtensions1._3_6_2_LocalFunctionsAndExtensions1Kt.main(3.6_2_LocalFunctionsAndExtensions1.kt:20)
 * 	at ch03.ex6_2_LocalFunctionsAndExtensions1._3_6_2_LocalFunctionsAndExtensions1Kt.main(3.6_2_LocalFunctionsAndExtensions1.kt)
 */