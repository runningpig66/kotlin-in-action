package ch07.ex4_1_DestructuringDeclarationsAndLoops

/**
 * @author runningpig66
 * @date 2025/12/9 周二
 * @time 14:58
 * §7.4 解构声明和组件函数 §7.4.1 解构声明和循环
 * P.195 代码清单 7.16 用解构声明来遍历 map
 *
 * 解构声明不仅可以用作函数中的顶层语句，还可以用在其他可以声明变量的地方，例如 in 循环。
 * 一个很好的例子，是枚举 map 中的条目。下面是一个小例子，使用这个语法打印给定 map 中的所有条目。
 */
fun printEntries(map: Map<String, String>) {
    // 在 in 循环中用解构声明
    for ((key, value) in map) {
        println("$key -> $value")
    }
}

fun main() {
    val map: Map<String, String> = mapOf("Oracle" to "Java", "JetBrains" to "Kotlin")
    printEntries(map)
}
/* Output:
Oracle -> Java
JetBrains -> Kotlin
 */
