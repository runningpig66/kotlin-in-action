package ch05.ex2_2_2_AllAnyCountFind1

/**
 * @author runningpig66
 * @date 2025/11/28 周五
 * @time 11:20
 * §5.2 集合的函数式 API §5.2.2 "all" "any" "count" 和 "find": 对集合应用判断式
 * P.118 代码清单 all any
 *
 * 注意，!all（“不是所有”）加上某个条件，可以用 any 加上这个条件的取反来替换，反之亦然。
 * 为了让你的代码更容易理解，应该选择前面不需要否定符号的函数：
 */
fun main() {
    val list = listOf(1, 2, 3)
    // 第一行检查是保证不是所有的元素都等于 3. 这和至少有一个元素不是 3 是一个意思，这正是你在第二行用 any 做的检查。
    println(!list.all { it == 3 }) // ！否定不明显，这种情况最好使用 "any"
    println(list.any { it != 3 }) // lambda 参数中的条件要取反
}
/* Output:
true
true
 */
