package ch04.ex3_1_1_StringRepresentationToString

/**
 * @author runningpig66
 * @date 2025/11/23 周日
 * @time 0:28
 * §4.3 编译器生成的方法：数据类和类委托 §4.3.1 通用对象方法 字符串表示：toString()
 * P.89 代码清单 4.17 Client 类的最初声明
 * P.89 代码清单 4.18 为 Client 实现 toString()
 */
class Client(val name: String, val postalCode: Int) {
    override fun toString() = "Client(name=$name, postalCode=$postalCode)"
}

fun main() {
    val client1 = Client("Alice", 342562)
    println(client1)
}
/* Output:
Client(name=Alice, postalCode=342562)
 */
