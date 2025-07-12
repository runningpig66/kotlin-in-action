package ch03.ValidateUser

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
/** Output:
 * Exception in thread "main" java.lang.IllegalArgumentException: Can't save user 1: empty Name
 * 	at ch03.ValidateUser._3_6_1_ValidateUserKt.saveUser(3.6_1_ValidateUser.kt:9)
 * 	at ch03.ValidateUser._3_6_1_ValidateUserKt.main(3.6_1_ValidateUser.kt:18)
 * 	at ch03.ValidateUser._3_6_1_ValidateUserKt.main(3.6_1_ValidateUser.kt)
 */