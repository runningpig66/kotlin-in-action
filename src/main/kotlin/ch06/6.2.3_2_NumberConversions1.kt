package ch06.ex2_3_2_NumberConversions1

// 代码清单 P.161
fun foo(l: Long) = println(l)

fun main() {
    val b: Byte = 1
    // + 可以进行字节类型和长整型参数的计算
    val l = b + 1L
    // 编译器认为 42 是一个长整型
    foo(42)
}
/** Output:
 * 42
 */