@file:JvmName("StringFunctions") // 注指定类名
package strings // 包的声明跟在文件注解之后

/**
 * @author runningpig66
 * @date 2025/11/19 周三
 * @time 2:40
 * 3.2 让函数更好调：3.2.3 消除静态工具类：顶层函数和属性
 * P.51 代码清单 3.3 声明 joinToString() 作为顶层函数
 *
 * 可以看到 Kotlin 编译生成的类的名称（见 JoinToStringTest.java 注释 [1]），对应于包含函数的文件的名称。
 * 这个文件中的所有顶层函数编译为这个类的静态函数。
 *
 * 要改变包含 Kotlin 顶层函数生成的类的名称，需要为这个文件添加 @file:JvmName("") 注解，
 * 将其放到这个文件的开头，位于包名的前面。
 */
fun <T> joinToString(
    collection: Collection<T>,
    separator: String = ", ",
    prefix: String = "",
    postfix: String = ""
): String {
    val result = StringBuilder(prefix)
    for ((index, element) in collection.withIndex()) {
        if (index > 0) {
            result.append(separator)
        }
        result.append(element)
    }
    result.append(postfix)
    return result.toString()
}

fun main() {
    val list = listOf(1, 2, 3)
    println(joinToString(list, separator = ", ", prefix = "[", postfix = "]"))
}
/* Output:
[1, 2, 3]
 */
