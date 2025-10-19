package ch02.ex5_2_1_TryAsAnExpression

import java.io.BufferedReader
import java.io.StringReader

/**
 * @author runningpig66
 * @date 2025/10/19 周日
 * @time 11:47
 * P.42 代码清单 2.28 把 try 当作表达式使用
 */
fun readNumber(reader: BufferedReader) {
    val number = try {
        Integer.parseInt(reader.readLine()) // 变成 "try" 表达式的值
    } catch (_: NumberFormatException) {
        return
    }
    println(number)
}

fun main() {
    val reader = BufferedReader(StringReader("not a number"))
    readNumber(reader) // 没有任何输
}
