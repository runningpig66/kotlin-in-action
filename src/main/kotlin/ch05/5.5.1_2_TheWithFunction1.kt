package ch05.ex5_1_2_TheWithFunction1

/**
 * @author runningpig66
 * @date 2025/11/30 周日
 * @time 14:46
 * §5.5 带接收者的 lambda: "with" 与 "apply" §5.5.1 "with" 函数
 * P.132 代码清单 5.17 使用 with 构建字母表
 *
 * Lambda 是一种类似普通函数的定义行为的方式。而带接收者的 lambda 是类似扩展函数的定义行为的方式。
 *
 * with 函数把它的第一个参数转换成作为第二个参数传给它的 lambda 的接收者。可以显式地通过 this 引用来访问这个接收者。
 * with 返回的值是执行 lambda 代码的结果，该结果就是 lambda 中的最后一个表达式（的值）。
 * public inline fun <T, R> with(receiver: T, block: T.() -> R): R
 */
fun alphabet(): String {
    val stringBuilder = StringBuilder()
    return with(stringBuilder) { // 指定接收者的值，你会调用它的方法
        for (letter in 'A'..'Z') {
            this.append(letter) // 通过显式的 "this" 来调用接收者值的方法
        }
        append("\nNow I know the alphabet!") // 省掉 "this" 也可以调用方法
        this.toString() // 从 lambda 返回值
    }
}

fun main() {
    println(alphabet())
}
/* Output:
ABCDEFGHIJKLMNOPQRSTUVWXYZ
Now I know the alphabet!
 */
