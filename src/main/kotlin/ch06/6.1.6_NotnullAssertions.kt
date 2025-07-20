package ch06.ex1_6_NotnullAssertions

// 代码清单 6.7 使用非空断言
fun ignoreNulls(s: String?) {
    val sNotNull: String = s!!
    println(sNotNull.length)
}

fun main() {
    ignoreNulls(null)
}
/** Output:
 * Exception in thread "main" java.lang.NullPointerException...
 */