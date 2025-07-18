package ch05.ex3_2_1_CreatingSequences

// 代码清单 5.12 生成并使用自然数序列
fun main() {
    val naturalNumbers = generateSequence(0) { it + 1 }
    // 从序列中依次取出元素，直到遇到第一个不满足条件的元素为止（后续元素将被忽略）
    val numbersTo100 = naturalNumbers.takeWhile { it <= 100 }
    println(numbersTo100.sum())
}