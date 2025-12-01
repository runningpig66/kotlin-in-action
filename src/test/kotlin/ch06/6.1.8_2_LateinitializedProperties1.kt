package ch06.ex1_8_2_LateinitializedProperties1

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

/**
 * @author runningpig66
 * @date 2025/12/1 周一
 * @time 17:04
 * §6.1 可空性 §6.1.8 延迟初始化的属性
 * P.150 代码清单 6.11 使用延迟初始化属性
 *
 * 注意，延迟初始化的属性都是 var, 因为需要在构造方法外修改它的值，
 * 而 val 属性会被编译成必须在构造方法中初始化的 final 字段。
 */
class MyService {
    fun performAction(): String = "foo"
}

class MyTest {
    // 声明一个不需要初始化器的非空类型的属性
    private lateinit var myService: MyService

    @BeforeEach
    fun setUp() {
        // 像之前的例子一样在 setUp 方法中初始化属性
        myService = MyService()
    }

    @Test
    fun testAction() {
        // 不需要 null 检查直接访问属性
        assertEquals("foo", myService.performAction())
    }
}
