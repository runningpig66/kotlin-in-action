package ch06.ex1_3_2_SafeCallOperator1

/**
 * @author runningpig66
 * @date 2025/12/1 周一
 * @time 11:28
 * §6.1 可空性 §6.1.3 安全调用运算符："?."
 * P.142 代码清单 6.2 使用安全调用处理可空属性
 *
 * 安全调用不光可以调用方法，也能用来访间属性。
 * 下面这个例子展示了一个具有可空属性的简单 Kotlin 类，以及访问这个属性时安全调用运算符的用法。
 */
class Employee(val name: String, val manager: Employee?)

fun managerName(employee: Employee): String? = employee.manager?.name

fun main() {
    val ceo = Employee("Da Boss", null)
    val developer = Employee("Bob Smith", ceo)
    println(managerName(developer))
    println(managerName(ceo))
}
/* Output:
Da Boss
null
 */
