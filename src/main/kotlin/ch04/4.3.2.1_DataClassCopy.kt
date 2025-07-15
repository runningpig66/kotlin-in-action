package ch04.ex3_2_1_DataClassCopy

// 代码清单 4.21 数据类 Client
data class Client(val name: String, val postalCode: Int)

fun main() {
    val bob = Client("Bob", 973293)
    println(bob.copy(postalCode = 382555))
}
/**
 * Client(name=Bob, postalCode=382555)
 */