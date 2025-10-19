package ch02.ex4_3_IteratingOverMaps

import java.util.*

/**
 * @author runningpig66
 * @date 2025/10/17 周五
 * @time 12:46
 * P.38 代码清单 2.24 初始化并迭代 map
 */
fun main() {
    val binaryReps = TreeMap<Char, String>() // 使用 TreeMap 让键排序
    for (c in 'A'..'F') { // 使用字符区间迭代从 A 到 F 之间的字
        val binary = Integer.toBinaryString(c.code) // 把 ASCII 码转换成二进制
        binaryReps[c] = binary // 根据键 c 把值存储到 map 中
    }

    for ((letter, binary) in binaryReps) { // 迭代 map, 把键和值赋给两个变量
        println("$letter = $binary")
    }

    val list = arrayListOf("10", "11", "1001")
    for ((index, element) in list.withIndex()) { // 迭代集合时使用下标
        println("$index: $element")
    }
}
/* Output:
A = 1000001
B = 1000010
C = 1000011
D = 1000100
E = 1000101
F = 1000110
0: 10
1: 11
2: 1001
 */
