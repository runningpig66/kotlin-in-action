package ch06.ex1_9_ExtensionsForNullableTypes

/**
 * @author runningpig66
 * @date 2025/12/1 周一
 * @time 17:13
 * §6.1 可空性 §6.1.9 可空类型的扩展
 * P.151 代码清单 6.12 用可空接受者调用扩展函数
 *
 * 为可空类型定义扩展函数是一种更强大的处理 null 值的方式。可以允许接收者为 null 的（扩展函数）调用，
 * 并在该函数中处理 null, 而不是在确保变量不为 null 之后再调用它的方法。只有扩展函数才能做到这一点，
 * 普通成员方法的调用是通过对象实例来分发的，因此实例为 null 时（成员方法）永远不能被执行。
 *
 * public inline fun CharSequence?.isNullOrBlank(): Boolean
 */
fun verifyUserInput(input: String?) {
    // 这里不需要安全调用，可以直接调用为可空接收者声明的扩展函数，这个函数会处理可能的 null 值。
    if (input.isNullOrBlank()) {
        println("Please fill in the required fields")
    }
}

fun main() {
    verifyUserInput(" ")
    // 这个接收者调用 isNullOrBlank 并不会导致任何异常
    verifyUserInput(null)
}
/* Output:
Please fill in the required fields
Please fill in the required fields
 */
