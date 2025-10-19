package ch02.ex5_1_TryCatchAndFinally

import java.io.BufferedReader
import java.io.StringReader

/**
 * @author runningpig66
 * @date 2025/10/19 周日
 * @time 11:04
 * P.41 代码清单 2.27 像在 Java 中一样使用 try
 */
fun readNumber(reader: BufferedReader): Int? { // 不必显式地指定这个函数可能抛出的异常
    try {
        val line = reader.readLine()
        return Integer.parseInt(line)
    } catch (_: NumberFormatException) { // 异常类型在右
        return null
    } finally { // "finally" 的作用和 Java 中的一样
        reader.close()
    }
}

fun main() {
    val reader = BufferedReader(StringReader("239"))
    println(readNumber(reader))
}
/* Output:
239
 */
