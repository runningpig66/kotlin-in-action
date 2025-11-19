package ch03.ex5_1_1_SplittingStrings

/**
 * @author runningpig66
 * @date 2025/11/20 周四
 * @time 4:35
 * 3.5 字符串和正则表达式的处理：3.5.1 分割字符串
 * P.62 代码清单
 *
 * notes: SplittingStrings.md
 * 什么是“字符类”，为什么要写成 \\。
 */

fun main() {
    println("12.345-6.A".split("\\.|-".toRegex())) // 显式地创建一个正则表达式
    // 使用正则表达式字符类
    println("12.345-6.A".split("[.\\-]".toRegex()))
    // 在正则的字符类 [] 中，如果 - 出现在开头或结尾，它就被当作普通字符（字面量的减号）无需转义
    println("12.345-6.A".split("[.-]".toRegex()))
}
/* Output:
[12, 345, 6, A]
[12, 345, 6, A]
[12, 345, 6, A]
 */
