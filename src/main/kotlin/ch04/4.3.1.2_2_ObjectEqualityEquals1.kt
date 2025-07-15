package ch04.ex3_1_2_2_ObjectEqualityEquals1

// 代码清单 4.19 为 Client 类实现 equals()
class Client(val name: String, val postalCode: Int) {
    override fun equals(other: Any?): Boolean {
        if (other == null || other !is Client) return false
        return name == other.name && postalCode == other.postalCode
    }

    override fun toString() = "Client(name=$name, postalCode=$postalCode)"
}

fun main() {
    val client1 = Client("Alice", 342562)
    val client2 = Client("Alice", 342562)
    println(client1 == client2)

    val processed = hashSetOf(Client("Alice", 342562))
    println(processed.contains(Client("Alice", 342562)))
}
/** Output:
 * true
 * false
 */