package ch04.ex4_3_CompanionObjects2

/**
 * @author runningpig66
 * @date 2025/11/24 周一
 * @time 11:06
 * §4.4 "object" 关键字：将声明一个类与创建一个实例结合起来 §4.4.3 作为普通对象使用的伴生对象
 * P.100 代码清单 4.28 在伴生对象中实现接口
 *
 * 类的伴生对象会同样被编译成常规对象：类中的一个引用了它的实例的静态字段。
 * 如果伴生对象没有命名，在 Java 代码中它可以通过 Companion 引用来访问。
 * 如果伴生对象有名字，那就用这个名字替代 Companion.
 *
 * TODO ch10 但是你也许要和这样的 Java 代码一起工作，它需要类中的成员是静态的。可以在对应的成员上使用 @JvmStatic 注解来达到这个目的。
 * 如采你想声明一个 static 字段，可以再在一个顶层属性或者声明在 object 中的属性上使用 @JvmField 注解。
 *
 * notes: CompanionObjects.md
 * （伴生）对象字节码对比
 */
interface JSONFactory<T> {
    fun fromJSON(jsonText: String): T
}

class Person(val name: String) {
    // 实现接口的伴生对象
    companion object : JSONFactory<Person> {
        override fun fromJSON(jsonText: String): Person {
            val name = jsonText.split("'")[1]
            return Person(name)
        }
    }

    override fun toString(): String {
        return "Person(name='$name')"
    }
}

fun <T> loadFromJSON(factory: JSONFactory<T>): T {
    return factory.fromJSON("{name: 'Fight'}")
}

fun main() {
    val person = loadFromJSON(Person.Companion)
    // 注意，Person 类的名字被当作 JSONFactory 的实例
    val person1 = loadFromJSON(Person)
    println(person)
    println(person1)
}
/* Output:
Person(name='Fight')
Person(name='Fight')
 */
