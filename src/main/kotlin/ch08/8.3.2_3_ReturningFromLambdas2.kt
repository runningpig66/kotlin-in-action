package ch08.ex3_2_3_ReturningFromLambdas2

/**
 * @author runningpig66
 * @date 2025/12/22 周一
 * @time 22:59
 * §8.3 高阶函数中的控制流 §8.3.2 从 lambda 返回：使用标签返回
 * P.228 代码清单 带标签的 "this" 表达式
 *
 * 同样的规则也适用于 this 表达式的标签。在第 5 章我们讨论了带接收者的 lambda————包含一个隐式上下文对象的 lambda
 * （第 11 章会解释如何写一个以带接收者的 lambda 作为参数的函数）可以通过一个 this 引用去访问。
 * 如果你给带接收者的 lambda 指定标签，就可以通过对应的带有标签的 this 表达式访问它的隐式接收者：
 *
 * 和 return 表达式中使用标签一样，可以显式地指定 lambda 表达式的标签，也可以使用函数名作为标签。
 */
fun main() {
    // 这个 lambda 的隐式接收者可以通过 this@sb 访问
    println(StringBuilder().apply sb@{
        listOf(1, 2, 3).apply {
            // 所有隐式接收者都可以被访问，外层的接收者通过显式的标签访问
            this@sb.append(
                // "this" 指向作用域内最近的隐式接收者
                this.toString()
            )
        }
    })
}
/* Output:
[1, 2, 3]
 */
