package ch03.ex6_3_LocalFunctionsAndExtensions2

class User(val id: Int, val name: String, val address: String)

fun saveUser(user: User) {
    fun validate(value: String, fieldName: String) {
        if (value.isEmpty()) {
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
/** Output:
 * Exception in thread "main" java.lang.IllegalArgumentException: Can't save user 1: empty Name
 * 	at ch03.ex6_3_LocalFunctionsAndExtensions2._3_6_3_LocalFunctionsAndExtensions2Kt.saveUser$validate(3.6_3_LocalFunctionsAndExtensions2.kt:8)
 * 	at ch03.ex6_3_LocalFunctionsAndExtensions2._3_6_3_LocalFunctionsAndExtensions2Kt.saveUser(3.6_3_LocalFunctionsAndExtensions2.kt:12)
 * 	at ch03.ex6_3_LocalFunctionsAndExtensions2._3_6_3_LocalFunctionsAndExtensions2Kt.main(3.6_3_LocalFunctionsAndExtensions2.kt:19)
 * 	at ch03.ex6_3_LocalFunctionsAndExtensions2._3_6_3_LocalFunctionsAndExtensions2Kt.main(3.6_3_LocalFunctionsAndExtensions2.kt)
 */