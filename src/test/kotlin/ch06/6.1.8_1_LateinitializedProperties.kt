package ch06.ex1_8_1_LateinitializedProperties

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

// 代码清单 6.10 使用非空断言访问可空属性
class MyService {
    fun performAction(): String = "foo"
}

class MyTest {
    private var myService: MyService? = null

    @BeforeEach
    fun setUp() {
        myService = MyService()
    }

    @Test
    fun testAction() {
        assertEquals("foo", myService!!.performAction())
    }
}