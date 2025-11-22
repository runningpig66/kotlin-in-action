package ch04.ex3_1_2_2_ObjectEqualityEquals1

/**
 * @author runningpig66
 * @date 2025/11/23 周日
 * @time 0:42
 * §4.3 编译器生成的方法：数据类和类委托 §4.3.1 通用对象方法 对象相等性：equals()
 * P.90 代码清单 4.19 为 Client 类实现 equals()
 */
class Client(val name: String, val postalCode: Int) {
    // "Any" 是 java.lang.Object 的模拟：Kotlin 中所有类的父类。可空类型 "Any?" 意味着 "other" 是可以为空的
    override fun equals(other: Any?): Boolean {
        // 检查 "other" 是不是一个 Client:
        // Kotlin 中的 is 检查是 Java 中 instanceof 的模拟，用来检查一个值是否为一个指定的类型。
        if (other == null || other !is Client) {
            return false
        }
        // 检查对应的属性是否相等
        return name == other.name && postalCode == other.postalCode
    }

    override fun toString() = "Client(name=$name, postalCode=$postalCode)"
}

fun main() {
    val client1 = Client("Alice", 342562)
    val client2 = Client("Alice", 342562)
    println(client1 == client2)

    // Client 类缺少了 hashCode 方法。因此它违反了通用的 hashCode 契约：如果两个对象相等，它们必须有着相同的 hash 值。
    // processed set 是一个 HashSet. 在 HashSet 中值是以一种优化过的方式来比较的：
    // 首先比较它们的 hash 值，然后只有当它们相等时才会去比较直正的值。
    val processed = hashSetOf(Client("Alice", 342562))
    println(processed.contains(Client("Alice", 342562)))
}
/* Output:
true
false
 */
