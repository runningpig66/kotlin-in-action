package ch06.ex1_10_NullabilityOfTypeParameters2

// 代码清单 6.14 为类型参数声明非空上界
fun <T : Any> printHashCode(t: T) {
    println(t.hashCode())
}

fun main() {
    // Null cannot be a value of a non-null type 'T (of fun <T : Any> printHashCode)'.
//    printHashCode(null)
    printHashCode(42)
}
/** Output:
 * 42
 */