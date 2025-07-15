package ch04.ex1_1_1_InterfacesInKotlin

// 代码清单 4.1 声明一个简单的接口
interface Clickable {
    fun click()
}

// 代码清单 4.2 实现一个简单接口
class Button : Clickable {
    override fun click() = println("I was clicked")
}

fun main() {
    Button().click()
}
/** Output:
 * I was clicked
 */