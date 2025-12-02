package ch06.ex2_6_NothingType

/**
 * @author runningpig66
 * @date 2025/12/2 周二
 * @time 13:38
 * §6.2 基本数据类型和其他基本类型 §6.2.6 Nothing 类型：“这个函数永不返回”
 * P.164 代码清单
 *
 * 对某些 Kotlin 函数来说，“返回类型”的概念没有任何意义，因为它们从来不会成功地结束。
 * 当分析调用这样函数的代码时，知道函数永远不会正常终止是很有帮助的。Kotlin 使用一种特殊的返回类型 Nothing 来表示：
 * Nothing 类型没有任何值，只有被当作函数返回值使用，或者被当作泛型函数返回值的类型参数使用才会有意义。
 * 在其他所有情况下，声明一个不能存储任何值的变量没有任何意义。
 *
 * 下面这个例子展示了在类型系统中拥有 Nothing 为什么极其有用。编译器知道这种返回类型的函数从不正常终止，
 * 然后在分析调用这个函数的代码时利用这个信息。在下面这个例子中，编译器会把 address 的类型推断成非空，
 * 因为它为 null 时的分支处理会始终抛出异常。
 */
class Address(val streetAddress: String, val zipCode: Int, val city: String, val country: String)
class Company(val name: String, val address: Address?)

fun fail(message: String): Nothing {
    throw IllegalStateException(message)
}

fun main() {
    // 注意，返回 Nothing 的函数可以放在 Elvis 运算符的右边来做先决条件检查：
    val company = Company("JetBrains", null)
    val address: Address = company.address ?: fail("No address")
}
/* Output:
Exception in thread "main" java.lang.IllegalStateException: No address
	at ch06.ex2_6_NothingType._6_2_6_NothingTypeKt.fail(6.2.6_NothingType.kt:19)
	at ch06.ex2_6_NothingType._6_2_6_NothingTypeKt.main(6.2.6_NothingType.kt:25)
	at ch06.ex2_6_NothingType._6_2_6_NothingTypeKt.main(6.2.6_NothingType.kt)
 */
