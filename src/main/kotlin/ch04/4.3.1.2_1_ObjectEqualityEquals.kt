package ch04.ex3_1_2_1_ObjectEqualityEquals

/**
 * @author runningpig66
 * @date 2025/11/23 周日
 * @time 0:34
 * §4.3 编译器生成的方法：数据类和类委托 §4.3.1 通用对象方法 对象相等性：equals()
 * P.90 代码清单
 */
class Client(val name: String, val postalCode: Int)

fun main() {
    val client1 = Client("Alice", 342562)
    val client2 = Client("Alice", 342562)
    // 在 Kotlin 中 == 检查对象是否相等，而不是比较引用。这里会编译成调用 "equals"
    println(client1 == client2)
}
/* Output:
false
 */
