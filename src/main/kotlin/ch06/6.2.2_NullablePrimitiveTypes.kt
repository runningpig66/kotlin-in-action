package ch06.ex2_2_NullablePrimitiveTypes

// 代码清单 6.20 使用可空的基本数据类型
data class Person(val name: String, val age: Int? = null) {
    fun idOlderThan(other: Person): Boolean? {
        if (age == null || other.age == null) return null
        return age > other.age
    }
}

fun main() {
    println(Person("Sam", 35).idOlderThan(Person("Amy", 42)))
    println(Person("Sam", 35).idOlderThan(Person("Jane")))
}
/** Output:
 * false
 * null
 */