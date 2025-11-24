package ch04.ex4_3_CompanionObjects3

/**
 * @author runningpig66
 * @date 2025/11/24 周一
 * @time 15:09
 * §4.4 "object" 关键字：将声明一个类与创建一个实例结合起来 §4.4.3 作为普通对象使用的伴生对象
 * P.102 代码清单 4.29 为伴生对象定义一个扩展函数
 *
 * 扩展函数允许你定义可以通过代码库中其他地方定义的类实例调用的方法。
 * 但是如果你需要定义可以通过类自身调用的方法，就像伴生对象方法或者是 Java 静态方法该怎么办呢？
 * 如果类有一个伴生对象，可以通过在其上定义扩展函数来做到这一点。
 * 具体来说，如果类 C 有一个伴生对象，并且在 C.Companion 上定义了一个扩展函数 func, 可以通过 C.func() 来调用它。
 *
 * 自己项目里的类：直接写 companion 里就行了，几乎不用伴生对象扩展。
 * （鸡肋）引入别人的 Kotlin 类（前提是已经有 companion object）：想要 ClassName.xxx() 风格，就可以用“伴生对象扩展”。
 */
class Person(val firstName: String, val lastName: String) {
    // 声明一个空的伴生对象
    companion object;
    override fun toString(): String {
        return "Person(firstName='$firstName', lastName='$lastName')"
    }
}

// 声明一个扩展函数
fun Person.Companion.fromJSON(json: String): Person {
    val jsonList = json.split("'")
    val firstName = jsonList[1]
    val lastName = jsonList[3]
    return Person(firstName, lastName)
}

fun main() {
    val person = Person.fromJSON("{first_name: 'Fight', last_name: 'Night'}")
    val person1 = Person.fromJSON("{first_name: 'Fight', last_name: 'Night'}")
    println(person)
    println(person1)
}
/* Output:
Person(firstName='Fight', lastName='Night')
Person(firstName='Fight', lastName='Night')
 */
