package ch06.ex1_3_2_SafeCallOperator1

// 代码清单 6.2 使用安全调用处理可空属性
class Employee(val name: String, val manager: Employee?)

fun managerName(employee: Employee): String? = employee.manager?.name

fun main() {
    val ceo = Employee("Mike", null)
    val developer = Employee("Bob Smith", ceo)
    println(managerName(developer))
    println(managerName(ceo))
}
/** Output:
 * Mike
 * null
 */