package ch06.ex1_5_SafeCastsAs

/**
 * @author runningpig66
 * @date 2025/12/1 周一
 * @time 14:56
 * §6.1 可空性 §6.1.5 安全转换："as?"
 * P.145 代码清单 6.6 使用安全转换实现 equals
 *
 * as? 运算符尝试把值转换成指定的类型，如果值不是合适的类型就返回 null.
 * 一种常见的模式是把安全转换和 Elvis 运算符结合使用。例如，实现 equals 方法的时候这样的用法非常方便。
 */
class Person(val firstName: String, val lastName: String) {
    override fun equals(other: Any?): Boolean {
        // 检查类型，如果不匹配就返回 false. 智能转换也会生效：当你检查过类型并拒绝了 null 值，
        // 编译器就确定了变量 otherPerson 值的类型是 Person 并让你能够相应地使用它。
        val otherPerson: Person = other as? Person ?: return false
        // 在安全转换之后，变量 otherPerson 被智能地转换为 Person 类型
        return otherPerson.firstName == firstName &&
                otherPerson.lastName == lastName
    }

    override fun hashCode(): Int = firstName.hashCode() * 37 + lastName.hashCode()
}

fun main() {
    val p1 = Person("Dmitry", "Jemerov")
    val p2 = Person("Dmitry", "Jemerov")
    println(p1 == p2) // == 运算符会调用 "equals" 方法
    println(p1.equals(42))
}
/* Output:
true
false
 */
