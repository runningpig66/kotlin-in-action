package ch04.ex4_3_CompanionObjects1

// 代码清单 4.27 声明一个命名伴生对象
class Person(val name: String) {
    companion object Loader {
        fun fromJSON(jsonText: String): Person {
            val name = jsonText.split("'")[1]
            return Person(name)
        }
    }
}

fun main() {
    val person = Person.Loader.fromJSON("{name: 'Dmitry'}")
    println(person.name)
    val person2 = Person.fromJSON("{name: 'Brent'}")
    println(person2.name)
}
/** Output:
 * Dmitry
 * Brent
 */