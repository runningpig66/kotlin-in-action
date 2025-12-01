package ch06.ex1_10_NullabilityOfTypeParameters2

/**
 * @author runningpig66
 * @date 2025/12/1 周一
 * @time 18:39
 * §6.1 可空性 §6.1.10 类型参数的可空性
 * P.153 代码清单 6.14 为类型参数声明非空上界
 *
 * 在 6.1.10_NullabilityOfTypeParameters.kt 的 printHashCode 调用中，
 * 类型参数 T 推导出的类型是可空类型 Any?. 因此，尽管没有用问号结尾，实参 t 依然允许持有 null.
 * 要使类型参数非空，必须要为它指定一个非空的上界，那样泛型会拒绝可空值作为实参。
 * 注意必须使用问号结尾来标记类型为可空的，没有问号就是非空的。类型参数是这个规则唯一的例外。
 */
fun <T : Any> printHashCode(t: T) { // 现在 "T" 就不是可空的
    println(t.hashCode())
}

fun main() {
    // 这段代码是无法编译的：你不能传递 null, 因为期望的是非空值
    // Cannot infer type for type parameter 'T'. Specify it explicitly.
    // Null cannot be a value of a non-null type 'uninferred T (of fun <T : Any> printHashCode)'.
    // printHashCode(null)

    printHashCode(42)
}
/* Output:
42
 */
