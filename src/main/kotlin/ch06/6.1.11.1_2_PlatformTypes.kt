package ch06.ex1_11_1_2_PlatformTypes

import ch06.Person

/**
 * @author runningpig66
 * @date 2025/12/1 周一
 * @time 19:13
 * §6.1 可空性 §6.1.11 可空性和 Java: 平台类型
 * P.155 代码清单 6.16 不使用 null 检查访问 Java 类
 *
 * 平台类型本质上就是 Kotlin 不知道可空性信息的类型。既可以把它当作可空类型处理，也可以当作非空类型处理。
 * 这意味着，你要像在 Java 中一样，对你在这个类型上做的操作负有全部责任。
 */
fun yellAt(person: Person) {
    // uppercase() 调用的接收者 person.name 为 null, 所以这里会抛出异常
    println(person.name.uppercase() + "!!!")
}

fun main() {
    yellAt((Person(null)))
}
/* Output:
Exception in thread "main" java.lang.NullPointerException: getName(...) must not be null
	at ch06.ex1_11_1_2_PlatformTypes._6_1_11_1_2_PlatformTypesKt.yellAt(6.1.11.1_2_PlatformTypes.kt:17)
	at ch06.ex1_11_1_2_PlatformTypes._6_1_11_1_2_PlatformTypesKt.main(6.1.11.1_2_PlatformTypes.kt:21)
	at ch06.ex1_11_1_2_PlatformTypes._6_1_11_1_2_PlatformTypesKt.main(6.1.11.1_2_PlatformTypes.kt)
 */
