package ch04.ex3_1_2_2_ObjectEqualityEquals2

// 代码清单 4.20 为 Client 实现 hashCode(
class Client(val name: String, val postalCode: Int) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Client

        if (postalCode != other.postalCode) return false
        if (name != other.name) return false

        return true
    }

    override fun hashCode(): Int {
        var result = postalCode
        result = 31 * result + name.hashCode()
        return result
    }

    override fun toString(): String {
        return "Client(name='$name', postalCode=$postalCode)"
    }
}

fun main() {
    val processed = hashSetOf(Client("Alice", 342562))
    println(processed.contains(Client("Alice", 342562)))
}
/** Output:
 * true
 */