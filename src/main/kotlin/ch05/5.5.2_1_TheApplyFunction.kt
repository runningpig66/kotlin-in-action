package ch05.ex5_2_1_TheApplyFunction

/**
 * @author runningpig66
 * @date 2025/11/30 周日
 * @time 15:43
 * §5.5 带接收者的 lambda: "with" 与 "apply" §5.5.2 "apply" 函数
 * P.133 代码清单 5.19 使用 apply 构建字母表
 *
 * 让我们再一次重构 alphabet 函数，这一次用的是 apply.
 *
 * apply 函数几乎和 with 函数一模一样，唯一的区别是 apply 始终会返回作为实参传递给它的对象（换句话说，接收者对象）。
 * apply 被声明成一个扩展函数。它的接收者变成了作为实参的 lambda 的接收者。Lambda 执行之后，apply 返回已经初始化过的接收者实例，
 * public inline fun <T, R> with(receiver: T, block: T.() -> R): R
 * public inline fun <T> T.apply(block: T.() -> Unit): T
 */
fun alphabet() = StringBuilder().apply {
    for (letter in 'A'..'Z') {
        append(letter)
    }
    append("\nNow I know the alphabet!")
}.toString()

fun main() {
    println(alphabet())
}
/* Output:
ABCDEFGHIJKLMNOPQRSTUVWXYZ
Now I know the alphabet!
 */
