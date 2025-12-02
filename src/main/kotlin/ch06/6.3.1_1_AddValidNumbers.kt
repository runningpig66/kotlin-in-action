package ch06.AddValidNumbers

import java.io.BufferedReader
import java.io.StringReader

/**
 * @author runningpig66
 * @date 2025/12/2 周二
 * @time 14:18
 * §6.3 集合与数组 §6.3.1 可空性和集合
 * P.165 代码清单 6.21 创建一个包含可空值的集合
 * P.166 代码清单 6.22 使用可空值的集合
 *
 * Kotlin 完全支持类型参数的可空性。就像变量的类型可以加上 ? 字符来表示变量可以持有 null 一样，
 * 类型在被当作类型参数时也可以用同样的方式标记。我们通过下面这个函数的例子来看看它是如何工作的，
 * 这个函数从一个文件中读取文本行的列表，并尝试把每一行文本解析成一个数字。
 *
 * 注意，变量自已类型的可空性和用作类型参数的类型的可空性是有区别的。要小心决定什么是可空的：集合的元素还是集合本身？
 */
fun readNumbers(reader: BufferedReader): List<Int?> {
    // 创建包含可空 Int 值的列表
    val result = ArrayList<Int?>()
    for (line: String in reader.lineSequence()) {
        try {
            val number: Int = line.toInt()
            // 向列表添加整数（非空值）
            result.add(number)
        } catch (_: NumberFormatException) {
            // 向列表添加 null, 因为当前行不能被解析成整数
            result.add(null)
        }
    }
    return result
}

// 要搞清楚如何使用包含可空值的列表，我们写了一个函数来计算列表中有效数字之和，并单独地对无效数字计数。
fun addValidNumbers(numbers: List<Int?>) {
    var sumOfValidNumbers = 0
    var invalidNumbers = 0
    // 从列表中读取可空值
    for (number: Int? in numbers) {
        // 检查值是否为 null
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
    val numbers: List<Int?> = readNumbers(reader)
    addValidNumbers(numbers)
}
/* Output:
Sum of valid numbers: 43
Invalid numbers: 1
 */
