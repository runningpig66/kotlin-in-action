package ch02.ex1_4_2_StringTemplates1

/**
 * @author runningpig66
 * @date 2025/10/13 周一
 * @time 14:45
 * P.22 字符串模板 - 还可以引用更复杂的表达式，而不是仅限于简单的变量名称，只需要把表达式用花括号括起来：
 * {Program arguments: Kotlin!}
 */
fun main(args: Array<String>) {
    if (args.isNotEmpty()) {
        println("Hello, ${args[0]}")
    }
}
/* Output:
Hello, Kotlin!
 */
