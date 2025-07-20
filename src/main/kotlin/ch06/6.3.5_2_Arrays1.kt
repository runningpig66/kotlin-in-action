package ch06.ex3_5_2_Arrays1

// 代码清单 6.30 创建字符数组
fun main() {
    val array1: Array<Char> = arrayOf('a', 'b', 'c')
    val array2: Array<Char?> = arrayOfNulls<Char>(3)
    val letters = Array<String>(26) { i -> ('a' + i).toString() }
    println(array1.joinToString())
    println(array2.joinToString())
    println(letters.joinToString(""))
}
/** Output:
 * a, b, c
 * null, null, null
 * abcdefghijklmnopqrstuvwxyz
 */