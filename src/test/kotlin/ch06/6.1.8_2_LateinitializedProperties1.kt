package ch06.ex1_8_2_LateinitializedProperties1

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

// 代码清单 6.11 使用延迟初始化属性
class MyService {
    fun performAction(): String = "foo"
}

class MyTest {
    private lateinit var myService: MyService

    @BeforeEach
    fun setUp() {
        myService = MyService()
    }

    @Test
    fun testAction() {
        assertEquals("foo", myService.performAction())
    }
}