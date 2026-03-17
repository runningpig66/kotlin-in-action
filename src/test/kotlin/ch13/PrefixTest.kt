package ch13

import ch13.ex4_1_ChainingInfixCalls.should
import ch13.ex4_1_ChainingInfixCalls.startWith
import org.junit.jupiter.api.Test

/**
 * @author runningpig66
 * @date 3月16日 周一
 * @time 18:03
 * Listing 13.18 Expressing an assertion with the Kotest DSL
 * 清单 13.18 使用 Kotest DSL 表达断言
 */
class PrefixTest {
    @Test
    fun testKPrefix() {
        val s = "kotlin".uppercase()
        s should startWith("K")
    }
}
