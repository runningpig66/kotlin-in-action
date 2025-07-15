package ch04.ex2_4_AccessingABackingFieldFromAGetterOrSetter

// 代码清单 4.15 在 setter 中访问支持字段
class User(val name: String) {
    var address: String = "unspecified"
        set(value) {
            println(
                """
                Address was changed for $name:
                "$field" -> "$value".""".trimIndent()
            )
            field = value
        }
}

fun main() {
    val user = User("Alice")
    user.address = "Elsenheimerstrasse 47, 80687 Muenchen"
}
/** Output:
 * Address was changed for Alice:
 * "unspecified" -> "Elsenheimerstrasse 47, 80687 Muenchen".
 */