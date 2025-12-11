package ch07.ex5_5_2_StoringPropertyValuesInAMap1

/**
 * @author runningpig66
 * @date 2025/12/11 周四
 * @time 18:09
 * §7.5 重用属性访问的逻辑：委托属性 §7.5.5 在 map 中保存属性值
 * P.203 代码清单 7.26 使用委托属性把值存到 map 中
 *
 * 委托属性可以使用任意 map 来作为属性委托，来灵活来处理具有可变属性集的对象。
 */
class Person {
    private val _attributes: HashMap<String, String> = hashMapOf()

    fun setAttribute(attrName: String, value: String) {
        _attributes[attrName] = value
    }

    // 把 map 作为委托属性
    // 因为标准库已经在标准 Map 和 MutableMap 接口上定义了 getValue 和 setValue 扩展函数，
    // 所以这里可以直接这样用。属性的名称将自动用作在 map 中的键，属性值作为 map 中的值。
    val name: String by _attributes
}

fun main() {
    val p = Person()
    val data = mapOf("name" to "Dmitry", "company" to "JetBrains")
    for ((attrName, value) in data) {
        p.setAttribute(attrName, value)
    }
    println(p.name)
}
/* Output:
Dmitry
 */
