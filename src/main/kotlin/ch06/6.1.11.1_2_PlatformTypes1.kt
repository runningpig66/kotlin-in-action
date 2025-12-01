package ch06.ex1_11_1_2_PlatformTypes1

import ch06.Person

/**
 * @author runningpig66
 * @date 2025/12/1 周一
 * @time 19:23
 * §6.1 可空性 §6.1.11 可空性和 Java: 平台类型
 * P.155 代码清单 6.17 使用 null 检查来访问 Java 类
 *
 * 另外一个选择是把 getName() 的返回类型解释为可空的并安全地访问它。
 *
 * 使用 JavaAPI 时要特别小心。大部分的库都没有（可空性）注解，所以可以把所有类型都解释为非空，但那样会导致错误。
 * 为了避免错误，你应该阅读要用到的 Java 方法的文档（必要时还要查看它的实现），
 * 搞清楚它们什么时候会返回 null, 并给那些方法加上检查。
 */
fun yellAtSafe(person: Person) {
    println((person.name ?: "Anyone").uppercase() + "!!!")
}

fun main() {
    yellAtSafe(Person(null))

    // String! 表示法被 Kotlin 编译器用来表示来自 Java 代码的平台类型。你不能在自已的代码中使用这种语法。
    // 而且感叹号通常与问题的来源无关，所以通常可以忽略它。它只是强调类型的可空性是未知的。
    // 如前所述，你可以用你喜欢的方式来解释平台类型，既可以是可空的也可以是非空的，所以下面两种声明都是有效的：
    val person = Person(null)
    person.name // 平台类型：String!
    person.name // Java 的属性可以被当作可空……
    person.name // ……或者非空
}
/* Output:
ANYONE!!!
 */
