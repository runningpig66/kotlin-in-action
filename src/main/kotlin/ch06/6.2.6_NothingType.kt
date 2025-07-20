package ch06.ex2_6_NothingType

// 代码清单 P.164
fun fail(message: String): Nothing {
    throw IllegalStateException(message)
}

fun main() {
    fail("Error occurred")
}
/** Output:
 * Exception in thread "main" java.lang.IllegalStateException: Error occurred...
 */