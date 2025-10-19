package ch02.ex1_4_2_StringTemplates2

/**
 * @author runningpig66
 * @date 2025/10/13 周一
 * @time 14:50
 * P.22 字符串模板 - 还可以在双引号中直接嵌套双引号，只要它们处在某个表达式的范围内（即花括号内）：
 */
fun main(args: Array<String>) {
    println("Hello, ${if (args.isNotEmpty()) args[0] else "someone"}")
}
/* Output:
Hello, someone
 */
