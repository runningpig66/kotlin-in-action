package ch03.ex5_1_1_SplittingStrings

fun main() {
    println("12.345-6.A".split("\\.|-".toRegex()))
    // 使用正则表达式字符类
    println("12.345-6.A".split("[.\\-]".toRegex()))
    // 在正则的字符类 [] 中，如果 - 出现在开头或结尾，它就被当作普通字符（字面量的减号）无需转义
    println("12.345-6.A".split("[.-]".toRegex()))
}
/** Output:
 * [12, 345, 6, A]
 * [12, 345, 6, A]
 * [12, 345, 6, A]
 */