package ch04.ex4_3_CompanionObjects1

/**
 * @author runningpig66
 * @date 2025/11/24 周一
 * @time 10:37
 * §4.4 "object" 关键字：将声明一个类与创建一个实例结合起来 §4.4.3 作为普通对象使用的伴生对象
 * P.100 代码清单 4.27 声明一个命名伴生对象
 *
 * 伴生对象是一个声明在类中的普通对象。它可以有名字，实现一个接口或者有扩展函数或属性。
 *
 * notes: CompanionObjects.md
 * （伴生）对象字节码对比
 * notes: ObjectsDiff.md
 * 既然 companion object 和 普通嵌套 object 都是“嵌套单例”，为啥 Kotlin 还要区分成两种？
 */
class Person(val name: String) {
    // 在大多数情况下，通过包含伴生对象的类的名字来引用伴生对象，所以不必关心它的名字。但是如果需要你也可以指明；
    // 如果你省略了伴生对象的名字，默认的名字将会分配为 Companion。
    companion object Loader {
        fun fromJSON(jsonText: String): Person {
            val name = jsonText.split("'")[1]
            return Person(name)
        }
    }
}

fun main() {
    // 可以通过两种方式来调用 fromJSON
    val person = Person.fromJSON("{name: 'Dmitry'}")
    println(person.name)
    val person2 = Person.fromJSON("{name: 'Brent'}")
    println(person2.name)
}
/* Output:
Dmitry
Brent
 */
