package ch04.ex3_1_2_2_ObjectEqualityEquals2

/**
 * @author runningpig66
 * @date 2025/11/23 周日
 * @time 1:04
 * §4.3 编译器生成的方法：数据类和类委托 §4.3.1 通用对象方法 Hash容器：hashCode()
 * P.91 代码清单 4.20 为 Client 实现 hashCode()
 *
 * notes: ClientEquals.md
 * 以下两者的 equals() 实现方式有什么区别？
 */
class Client(val name: String, val postalCode: Int) {
    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }
        if (javaClass != other?.javaClass) {
            return false
        }
        other as Client
        if (postalCode != other.postalCode) {
            return false
        }
        if (name != other.name) {
            return false
        }
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
/* Output:
true
 */
