package ch06.ex3_5_1_Arrays

/**
 * @author runningpig66
 * @date 2025/12/2 周二
 * @time 20:19
 * §6.3 集合与数组 §6.3.5 对象和基本数据类型的数组
 * P.173 代码清单 6.29 使用数组
 * {Program arguments: 1 2 3 4}
 *
 * Kotlin 数组的语法出现在了每个例子中，因为数组是 Java main 函数标准签名的一部分。下面给你一些关于它的提示：
 */
fun main(args: Array<String>) {
    // 使扩展属性 array.indices 在下标的范围内送代
    for (index: Int in args.indices) {
        // 通过下标使用 array[index] 访问元素
        println("Argument $index is: ${args[index]}")
    }
}
/* Output:
Argument 0 is: 1
Argument 1 is: 2
Argument 2 is: 3
Argument 3 is: 4
 */
