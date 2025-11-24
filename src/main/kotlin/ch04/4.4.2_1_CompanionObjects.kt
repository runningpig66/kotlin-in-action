package ch04.ex4_2_1_CompanionObjects

/**
 * @author runningpig66
 * @date 2025/11/24 周一
 * @time 9:28
 * §4.4 "object" 关键字：将声明一个类与创建一个实例结合起来 §4.4.2 伴生对象：工厂方法和静态成员的地盘
 * P.98 代码清单
 */

class A {
    companion object {
        fun bar() {
            println("Companion object called")
        }
    }
}

fun main() {
    A.bar()
    A.bar() // Redundant Companion reference
}
/* Output:
Companion object called
Companion object called
 */
