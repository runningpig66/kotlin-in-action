package ch02.ex1_4_1_StringTemplates

/**
 * @author runningpig66
 * @date 2025/10/13 周一
 * @time 14:33
 * P.22 代码清单 2.2 使用字符串模板
 */
fun main(args: Array<String>) {
    val name = if (args.size > 0) args[0] else "Kotlin"
    println("Hello, $name!")
}
/* Output:
Hello, Kotlin!
 */
