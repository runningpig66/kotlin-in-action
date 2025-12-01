package ch06.ex1_8_1_LateinitializedProperties

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

/**
 * @author runningpig66
 * @date 2025/12/1 周一
 * @time 16:53
 * §6.1 可空性 §6.1.8 延迟初始化的属性
 * P.150 代码清单 6.10 使用非空断言访问可空属性
 */
class MyService {
    fun performAction(): String = "foo"
}

class MyTest {
    // 声明一个可空类型的属性井初始化为 null
    private var myService: MyService? = null

    @BeforeEach
    fun setUp() {
        // 在 setUp 方法中提供真正的初始化器
        myService = MyService()
    }

    @Test
    fun testAction() {
        // 必须注意可空性：要么用 "!!", 要么用 "?.".
        assertEquals("foo", myService!!.performAction())
    }
}
