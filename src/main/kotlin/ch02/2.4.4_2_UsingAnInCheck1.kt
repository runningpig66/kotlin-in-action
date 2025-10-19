package ch02.ex4_4_2_UsingAnInCheck1

/**
 * @author runningpig66
 * @date 2025/10/19 周日
 * @time 10:34
 * P.40 代码清单 2.26 用 in 检查作为 when 分支
 */
fun recognize(c: Char) = when (c) {
    in '0'..'9' -> "It's a digit!" // 检查值是否在 0 到 9 的区间之内
    in 'a'..'z', in 'A'..'Z' -> "It's a letter!" // 可以组合多种区间
    else -> "I don't know..."
}

fun main() {
    println(recognize('8'))
    println("Kotlin" in "Java".."Scala") // 结果和 "Java" <= "Kotlin" && "Kotlin" <= "Scala" 一样
    println("Kotlin" in setOf("Java", "Scala")) // 这个集合不包含字符串 "Kotlin"
}
/* Output:
It's a digit!
true
false
 */
