package ch07.ex2_2_1_OrderingOperators

/**
 * @author runningpig66
 * @date 2025/12/4 周四
 * @time 22:48
 * §7.2 重载比较运算符 §7.2.2 排序运算符：compareTo
 * P.187 代码清单 7.8 实现 compareTo 方法
 *
 * Kotlin 支持和 Java 相同的 Comparable 接口。但是接口中定义的 compareTo 方法可以按约定调用，
 * 比较运算符 (<, >, <= 和 >=) 的使用将被转换为 compareTo,（两个对象的比较被转换为 compareTo 函数的调用，然后结果与零比较）
 * compareTo 的返回类型必须为 Int. p1 < p2 表达式等价于 p1.compareTo(p2) < 0.其他比较运算符的运算方式也是完全一样的。
 * 实现了 Comparable 接口的对象不仅可以在 Kotlin 代码中用来比较，还可以被 Java 函数（比如用于对集合进行排序的功能）进行比较。
 *
 * public actual operator fun compareTo(other: T): Int
 * public fun <T> compareValuesBy(a: T, b: T, vararg selectors: (T) -> Comparable<*>?): Int
 */
// 因为没有明显的正确方式来比较两个点，所以让我们用旧的 Person 类作为例子，来看看如何实现这个方法。
// 这个实现将对地址薄排序（先比较名字中的姓氏，如果姓氏相同，再比较名字）。
class Person(val firstName: String, val lastName: String) : Comparable<Person> {
    // 与 equals 一样，operator 修饰符已经被用在了基类 Comparable 的接口中，因此在重写该接口时无须再重复。
    override fun compareTo(other: Person): Int {
        // 按顺序调用给定的方法，并比较它们的值。要注意如何使用 Kotlin 标准库中的 compareValuesBy 函数来简洁地实现 compareTo 方法。
        return compareValuesBy(
            this, other, Person::lastName, Person::firstName
        )
    }
}

fun main() {
    val p1 = Person("Alice", "Smith")
    val p2 = Person("Bob", "Johnson")
    println(p1 < p2)
    println(p1.compareTo(p2) < 0)
}
/* Output:
false
false
 */
