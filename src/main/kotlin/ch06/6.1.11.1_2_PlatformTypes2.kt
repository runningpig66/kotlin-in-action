package ch06.ex1_11_1_2_PlatformTypes2

import ch06.StringProcessor

// 代码清单 6.19 实现 Java 接口时使用不同的参数可空性
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