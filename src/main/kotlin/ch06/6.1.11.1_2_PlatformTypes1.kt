package ch06.ex1_11_1_2_PlatformTypes1

import ch06.Person

// 代码清单 6.17 使用 null 检查来访问 Java 类
fun yellAfSafe(person: Person) {
    println((person.name ?: "Anyone").uppercase() + "!!!")
}

fun main() {
    yellAfSafe(Person(null))
}
/** Output:
 * ANYONE!!!
 */