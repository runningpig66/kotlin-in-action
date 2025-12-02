package ch06.ex3_1_2_NullabilityAndCollections1

import java.io.BufferedReader
import java.io.StringReader

/**
 * @author runningpig66
 * @date 2025/12/2 周二
 * @time 15:18
 * §6.3 集合与数组 §6.3.1 可空性和集合
 * P.165 - 166 代码清单 6.23 对包含可空值的集合使用 filterNotNull
 *
 * [1] 使用 toIntOrNull() 来简化 6.3.1_1_AddValidNumbers.kt 的例子
 * [2] 遍历一个包含可空值的集合并过滤掉 null 是一个非常常见的操作，
 * 因此 Kotlin 提供了一个标准库函数 filterNotNull 来完成它。这里可以用它来大大简化前面的例子。
 *
 * public fun String.toIntOrNull(): Int? = toIntOrNull(radix = 10)
 * public fun <T : Any> Iterable<T?>.filterNotNull(): List<T>
 */
fun readNumbers(reader: BufferedReader): List<Int?> {
    val result = ArrayList<Int?>()
    for (line: String in reader.lineSequence()) {
        val number: Int? = line.toIntOrNull() // [1]
        result.add(number)
    }
    return result
}

fun addValidNumbers(numbers: List<Int?>) {
    val validNumbers: List<Int> = numbers.filterNotNull() // [2]
    println("Sum of valid numbers: ${validNumbers.sum()}")
    println("Invalid numbers: ${numbers.size - validNumbers.size}")
}

fun main() {
    val reader = BufferedReader(StringReader("1\nabc\n42"))
    val numbers: List<Int?> = readNumbers(reader)
    addValidNumbers(numbers)
}
/* Output:
Sum of valid numbers: 43
Invalid numbers: 1
 */
