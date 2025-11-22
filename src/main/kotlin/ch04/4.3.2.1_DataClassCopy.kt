package ch04.ex3_2_1_DataClassCopy

/**
 * @author runningpig66
 * @date 2025/11/23 周日
 * @time 2:09
 * §4.3 编译器生成的方法：数据类和类委托 §4.3.2 数据类：自动生成通用方法的实现
 * P.92 代码清单 4.21 数据类 Client
 */
data class Client(val name: String, val postalCode: Int)

fun main() {
    val bob = Client("Bob", 973293)
    println(bob.copy(postalCode = 382555))
}
/* Output:
Client(name=Bob, postalCode=382555)
 */
