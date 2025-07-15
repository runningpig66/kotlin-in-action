package ch04.ex3_1_1_StringRepresentationToString

// 代码清单 4.17 Client 类的最初声明
// 代码清单 4.18 为 Client 实现 toString()
class Client(val name: String, val postalCode: Int) {
    override fun toString() = "Client(name=$name, postalCode=$postalCode)"
}

fun main() {
    val client1 = Client("Alice", 342562)
    println(client1)
}
/** Output:
 * Client(name=Alice, postalCode=342562)
 */
