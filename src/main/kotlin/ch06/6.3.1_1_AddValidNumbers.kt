package ch06.AddValidNumbers

import java.io.BufferedReader
import java.io.StringReader

// 代码清单 6.21 创建一个包含可空值的集合
fun readNumbers(reader: BufferedReader): List<Int?> {
    val result = ArrayList<Int?>()
    for (line in reader.lineSequence()) {
        try {
            val number = line.toInt()
            result.add(number)
        } catch (_: NumberFormatException) {
            result.add(null)
        }
        // since kotlin 1.1
//        result.add(line.toIntOrNull())
    }
    return result
}

// 代码清单 6.22 使用可空值的集合
fun addValidNumbers(numbers: List<Int?>) {
    var sumOfValidNumbers = 0
    var invalidNumbers = 0
    for (number in numbers) {
        if (number != null) {
            sumOfValidNumbers += number
        } else {
            invalidNumbers++
        }
    }
    println("Sum of valid numbers: $sumOfValidNumbers")
    println("Invalid numbers: $invalidNumbers")
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