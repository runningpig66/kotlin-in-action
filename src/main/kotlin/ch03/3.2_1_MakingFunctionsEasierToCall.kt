package ch03.ex2_1_MakingFunctionsEasierToCall

/**
 * @author runningpig66
 * @date 2025/11/19 周三
 * @time 1:37
 * §3.2 让函数更好调用
 * P.47 代码清单
 */

fun main() {
    val list = listOf(1, 2, 3)
    println(list) // 触发 toString() 的调用
}
/* Output:
[1, 2, 3]
 */
