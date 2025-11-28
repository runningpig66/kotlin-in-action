package ch05.ex3_2_1_CreatingSequences

/**
 * @author runningpig66
 * @date 2025/11/28 周五
 * @time 17:53
 * §5.3 情性集合操作：序列 §5.3.2 创建序列
 * P.125 代码清单 5.12 生成并使用自然数序列
 *
 * public fun <T : Any> generateSequence(seed: T?, nextFunction: (T) -> T?): Sequence<T>
 * public fun <T> Sequence<T>.takeWhile(predicate: (T) -> Boolean): Sequence<T>
 */
fun main() {
    // 给定序列中的前一个元素，这个函数会计算出下一个元素
    val naturalNumbers = generateSequence(0) { it + 1 }
    // 中间操作：从序列中依次取出元素，直到遇到第一个不满足条件的元素为止（后续元素将被忽略）
    val numbersTo100 = naturalNumbers.takeWhile { it <= 100 }
    // 当获取结果 "sum" 时，所有被推迟的操作都被执行
    println(numbersTo100.sum())
}
/* Output:
5050
 */
