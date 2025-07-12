package ch03.ex6_4_LocalFunctionsAndExtensions3

class User(val id: Int, val name: String, val address: String)

fun User.validateBeforeSave() {
    fun validate(value: String, fieldName: String) {
        if (value.isEmpty()) {
            throw IllegalArgumentException("Can't save user $id: empty $fieldName")
        }
    }
    validate(name, "Name")
    validate(address, "Address")
}

fun saveUser(user: User) {
    user.validateBeforeSave()
    // Save user to the database
}

fun main() {
    saveUser(User(1, "", ""))
}
/** Output:
 * Exception in thread "main" java.lang.IllegalArgumentException: Can't save user 1: empty Name
 * 	at ch03.ex6_4_LocalFunctionsAndExtensions3._3_6_4_LocalFunctionsAndExtensions3Kt.validateBeforeSave$validate(3.6_4_LocalFunctionsAndExtensions3.kt:8)
 * 	at ch03.ex6_4_LocalFunctionsAndExtensions3._3_6_4_LocalFunctionsAndExtensions3Kt.validateBeforeSave(3.6_4_LocalFunctionsAndExtensions3.kt:11)
 * 	at ch03.ex6_4_LocalFunctionsAndExtensions3._3_6_4_LocalFunctionsAndExtensions3Kt.saveUser(3.6_4_LocalFunctionsAndExtensions3.kt:16)
 * 	at ch03.ex6_4_LocalFunctionsAndExtensions3._3_6_4_LocalFunctionsAndExtensions3Kt.main(3.6_4_LocalFunctionsAndExtensions3.kt:21)
 * 	at ch03.ex6_4_LocalFunctionsAndExtensions3._3_6_4_LocalFunctionsAndExtensions3Kt.main(3.6_4_LocalFunctionsAndExtensions3.kt)
 */