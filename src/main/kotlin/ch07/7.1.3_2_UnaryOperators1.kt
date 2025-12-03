package ch07.ex1_3_2_UnaryOperators1

import java.math.BigDecimal

/**
 * @author runningpig66
 * @date 2025/12/3 周三
 * @time 18:39
 * §7.1 重载算术运算符 §7.1.3 重载一元运算符
 * P.185 代码清单 7.5 定义一个一元运算符
 *
 * 当你定义 inc 和 dec 函数来重载自增和自减的运算符时，编译器自动支持与普通数字类型的前缀和后缀自增运算符相同的语义。
 * 考虑一下用来重载 BigDecimal 类的 ++ 运算符的这个例子。
 */
operator fun BigDecimal.inc() = this + BigDecimal.ONE

fun main() {
    var bd = BigDecimal.ZERO
    // 在第一个 println 语句执行后增加
    println(bd++)
    // 在第二个 println 语句执行前增加
    println(++bd)
}
/* Output:
0
2
 */
