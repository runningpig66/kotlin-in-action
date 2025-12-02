package ch06.ex2_2_NullablePrimitiveTypes

/**
 * @author runningpig66
 * @date 2025/12/2 周二
 * @time 11:06
 * §6.2 基本数据类型和其他基本类型 §6.2.2 可空的基本数据类型：Int?, Boolean?, 及其他
 * P.159 代码清单 6.20 使用可空的基本数据类型
 *
 * Kotlin 中的可空类型不能用 Java 的基本数据类型表示，因为 null 只能被存储在 Java 的引用类型的变量中。
 * 这意味着任何时候只要使用了基本数据类型的可空版本，它就会编译成对应的包装类型。
 */
data class Person(val name: String, val age: Int? = null) {
    fun isOlderThan(other: Person): Boolean? {
        // 注意，普通的可空性规则如何在这里应用。你不能就这样比较 Int? 类型的两个值，因为它们之中任何一个都可能为 null.
        // 相反，你必须检查两个值都不为 null. 然后，编译器才允许你正常地比较它们。
        if (age == null || other.age == null) {
            return null
        }
        return age > other.age
    }
}

fun main() {
    println(Person("Sam", 35).isOlderThan(Person("Amy", 42)))
    println(Person("Sam", 35).isOlderThan(Person("Jane")))
}
/* Output:
false
null
 */
