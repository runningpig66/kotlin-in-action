package ch13.ex4_1_ChainingInfixCalls

/**
 * @author runningpig66
 * @date 3月16日 周一
 * @time 15:18
 * 13.4.1 Chaining infix calls: The should function in test frameworks
 * 13.4.1 链式中缀调用：测试框架中的 should 函数
 * Let’s look at an example that uses the DSL of Kotest (https://github.com/kotest/kotest)
 * notes: ch13/notes/4.1_ChainingInfixCallsNotes.kt
 */
interface Matcher<T> {
    fun test(value: T)
}

// Listing 13.19 Implementing the should function
// 清单 13.19 实现 should 函数
infix fun <T> T.should(matcher: Matcher<T>) = matcher.test(this)

// Listing 13.20 Defining a matcher for the Kotest DSL
// 清单 13.20 为 Kotest DSL 定义匹配器
fun startWith(prefix: String): Matcher<String> {
    return object : Matcher<String> {
        override fun test(value: String) {
            if (!value.startsWith(prefix)) {
                throw AssertionError("$value does not start with $prefix")
            }
        }
    }
}

fun main() {
    val s = "kotlin".uppercase()
    s should startWith("K")
    println("OK!")

    try {
        s should startWith("Java")
    } catch (e: AssertionError) {
        println(e.message)
    }
}
/* Output:
OK!
KOTLIN does not start with Java
 */
