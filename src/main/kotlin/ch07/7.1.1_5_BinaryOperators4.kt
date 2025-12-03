package ch07.ex1_1_5_BinaryOperators4

/**
 * @author runningpig66
 * @date 2025/12/3 周三
 * @time 11:47
 * §7.1 重载算术运算符 §7.1.1 重载二元算术运算
 * P.183 代码清单
 *
 * 带符号左移
 * public actual infix fun shl(bitCount: Int): Int
 * 带符号右移
 * public actual infix fun shr(bitCount: Int): Int
 * 无符号右移
 * public actual infix fun ushr(bitCount: Int): Int
 * 按位与
 * public actual infix fun and(other: Int): Int
 * 按位或
 * public actual infix fun or(other: Int): Int
 * 按位异或
 * public actual infix fun xor(other: Int): Int
 * 按位取反
 * public actual fun inv(): Int
 */
fun main(args: Array<String>) {
    println(0x0F and 0xF0)
    println(0x0F or 0xF0)
    println(0x1 shl 4)
}
/* Output:
0
255
16
 */
