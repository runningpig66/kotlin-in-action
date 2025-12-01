package ch06.ex1_11_1_2_PlatformTypes2

import ch06.StringProcessor

/**
 * @author runningpig66
 * @date 2025/12/1 周一
 * @time 20:39
 * §6.1 可空性 §6.1.11 可空性和 Java: 继承
 * P.157 代码清单 6.19 实现 Java 接口时使用不同的参数可空性
 *
 * 当在 Kotlin 中重写 Java 的方法时，可以选择把参数和返回类型定义成可空的，也可以选择把它们定义成非空的。
 */
class StringPrinter : StringProcessor {
    override fun process(value: String) {
        println(value)
    }
}

class NullableStringPrinter : StringProcessor {
    override fun process(value: String?) {
        value?.let { println(it) }
    }
}
