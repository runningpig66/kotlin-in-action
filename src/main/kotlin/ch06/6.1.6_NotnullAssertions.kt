package ch06.ex1_6_NotnullAssertions

/**
 * @author runningpig66
 * @date 2025/12/1 周一
 * @time 15:32
 * §6.1 可空性 §6.1.6 非空断言："!!"
 * P.146 代码清单 6.7 使用非空断言
 *
 * 非空断言是 Kotlin 提供给你的最简单直率的处理可空类型值的工具。它使用双感叹号表示，可以把任何值转换成非空类型。
 * 如果对 null 值做非空断言，则会抛出异常。
 */
fun ignoreNulls(s: String?) {
    // 但是注意异常抛出的位置是非空断言所在的那一行，而不是接下来试图使用那个值的一行。
    val sNotNull: String = s!! // 异常指向这一行
    println(sNotNull.length)
}

fun main() {
    ignoreNulls(null)
}
/* Output:
Exception in thread "main" java.lang.NullPointerException
	at ch06.ex1_6_NotnullAssertions._6_1_6_NotnullAssertionsKt.ignoreNulls(6.1.6_NotnullAssertions.kt:15)
	at ch06.ex1_6_NotnullAssertions._6_1_6_NotnullAssertionsKt.main(6.1.6_NotnullAssertions.kt:20)
	at ch06.ex1_6_NotnullAssertions._6_1_6_NotnullAssertionsKt.main(6.1.6_NotnullAssertions.kt)
 */
