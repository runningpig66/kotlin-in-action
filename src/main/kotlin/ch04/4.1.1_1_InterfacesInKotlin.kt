package ch04.ex1_1_1_InterfacesInKotlin

/**
 * @author runningpig66
 * @date 2025/11/22 周六
 * @time 3:01
 * §4.1 定义类继承结构 §4.1.1 Kotlin 中的接口
 * P.70 代码清单 4.1 声明一个简单的接口
 * P.70 代码清单 4.2 实现一个简单接口
 */

interface Clickable {
    fun click()
}

class Button : Clickable {
    override fun click() = println("I was clicked")
}

fun main() {
    Button().click()
}
/* Output:
I was clicked
 */
