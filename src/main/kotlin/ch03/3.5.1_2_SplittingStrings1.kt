package ch03.ex5_1_2_SplittingStrings1

/**
 * @author runningpig66
 * @date 2025/11/20 周四
 * @time 5:28
 * 3.5 字符串和正则表达式的处理：3.5.1 分割字符串
 * P.63 代码清单
 *
 * 对于一些简单的情况，就不需要使用正则表达式了。
 * Kotlin 中的 split 扩展函数的其他重载支持任意数量的纯文本字符串分隔符：
 */

fun main() {
    println("12.345-6.A".split(".", "-"))
    println("12.345-6.A".split('.', '-'))
}
/* Output:
[12, 345, 6, A]
[12, 345, 6, A]
 */
