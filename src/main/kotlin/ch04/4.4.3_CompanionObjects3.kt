package ch04.ex4_3_CompanionObjects3

// 代码清单 4.29 为伴生对象定义一个扩展函数
class Person(val firstName: String, val lastName: String) {
    // 声明一个空的伴生对象
    companion object;
    override fun toString(): String {
        return "Person(firstName='$firstName', lastName='$lastName')"
    }
}

fun Person.Companion.fromJson(json: String): Person {
    val jsonList = json.split("'")
    val firstName = jsonList[1]
    val lastName = jsonList[3]
    return Person(firstName, lastName)
}

fun main() {
    val person = Person.fromJson("{first_name: 'Fight', last_name: 'Night'}")
    println(person)
}
/** Output:
 * Person(firstName='Fight', lastName='Night')
 */