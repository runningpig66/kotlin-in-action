package ch06.ex2_3_2_NumberConversions1

/**
 * @author runningpig66
 * @date 2025/12/2 周二
 * @time 11:35
 * §6.2 基本数据类型和其他基本类型 §6.2.3 数字转换
 * P.161 代码清单
 *
 * 算术运算符也被重载了，它们可以接收所有适当的数字类型。例如，下面这段代码并没有任何显式转换，但可以正确地工作：
 */
fun foo(l: Long) = println(l)
fun main() {
    val b: Byte = 1 // 常量有正确的类型
    val l = b + 1L // + 可以进行字节类型和长整型参数的计算
    foo(l) // 编译器认为 l 是一个长整型
    foo(42) // 编译器认为 42 是一个长整型
}
/* Output:
2
42
 */
