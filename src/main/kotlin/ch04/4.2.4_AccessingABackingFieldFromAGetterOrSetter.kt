package ch04.ex2_4_AccessingABackingFieldFromAGetterOrSetter

/**
 * @author runningpig66
 * @date 2025/11/22 周六
 * @time 23:50
 * §4.2 声明一个带非默认构造方法或属性的类 §4.2.4 通过 getter 或 setter 访问支持字段
 * P.86 代码清单 4.15 在 setter 中访问支持字段
 */
class User(val name: String) {
    var address: String = "unspecified"
        set(value) {
            println(
                """
                Address was changed for $name:
                "$field" -> "$value".""".trimIndent() // 读取支持字段的值
            )
            field = value // 更新支持字段的值
        }
}

fun main() {
    val user = User("Alice")
    user.address = "Elsenheimerstrasse 47, 80687 Muenchen"
}
/* Output:
Address was changed for Alice:
"unspecified" -> "Elsenheimerstrasse 47, 80687 Muenchen".
 */
