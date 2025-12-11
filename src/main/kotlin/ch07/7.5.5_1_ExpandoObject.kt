package ch07.ExpandoObject

/**
 * @author runningpig66
 * @date 2025/12/11 周四
 * @time 17:57
 * §7.5 重用属性访问的逻辑：委托属性 §7.5.5 在 map 中保存属性值
 * P.203 代码清单 7.25 定义一个属性，把值存到 map
 */
class Person {
    private val _attributes: HashMap<String, String> = hashMapOf()

    fun setAttribute(attrName: String, value: String) {
        _attributes[attrName] = value // 从 map 手动检索属性
    }

    val name: String
        get() = _attributes["name"]!!
}

fun main() {
    val p = Person()
    val data: Map<String, String> = mapOf("name" to "Dmitry", "company" to "JetBrains")
    for ((attrName, value) in data) {
        p.setAttribute(attrName, value)
    }
    println(p.name)
}
/* Output:
Dmitry
 */
