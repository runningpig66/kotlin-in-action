package ch06.ex3_1_2_NullabilityAndCollections1

import java.io.BufferedReader
import java.io.StringReader

// 代码清单 6.23 对包含可空值的集合使用 filterNotNull
fun readNumbers(reader: BufferedReader): List<Int?> {
    val result = ArrayList<Int?>()
    for (line in reader.lineSequence()) {
        result.add(line.toIntOrNull())
    }
    return result
}

fun addValidNumbers(numbers: List<Int?>) {
    val validNumbers = numbers.filterNotNull()
    println("Sum of valid numbers: ${validNumbers.sum()}")
    println("Invalid numbers: ${numbers.size - validNumbers.size}")
}

fun main() {
    val reader = BufferedReader(StringReader("1\nabc\n42"))
    val numbers = readNumbers(reader)
    addValidNumbers(numbers)
}
/** Output:
 * Sum of valid numbers: 43
 * Invalid numbers: 1
 */