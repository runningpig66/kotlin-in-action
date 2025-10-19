package ch02.ex5_2_2_TryAsAnExpression1

import java.io.BufferedReader
import java.io.StringReader

/**
 * @author runningpig66
 * @date 2025/10/19 周日
 * @time 11:53
 * P.43 代码清单 2.29 在 catch 中返回值
 */
fun readNumber(reader: BufferedReader) {
    val number = try {
        Integer.parseInt(reader.readLine()) // 没有任何异常发生时使用这个值
    } catch (_: NumberFormatException) {
        null // 发生异常的情况下使用 null
    }
    println(number)
}

fun main() {
    val reader = BufferedReader(StringReader("not a number"))
    readNumber(reader) // 抛出了一个异常，所以函数打印了 "null"
}
/* Output:
null
 */
