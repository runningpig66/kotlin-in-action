package ch04.ex4_3_CompanionObjects2

// 代码清单 4.28 在伴生对象中实现接口
interface JSONFactory<T> {
    fun fromJSON(jsonText: String): T
}

class Person(val name: String) {
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
    println(person)
}
/** Output:
 * Person(name='Fight')
 */