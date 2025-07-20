package ch06.ex1_11_1_2_PlatformTypes

import ch06.Person

// 代码清单 6.16 不使用 null 检查访问 Java 类
fun yellAt(person: Person) {
    println(person.name.uppercase() + "!!!")
}

fun main() {
    yellAt(Person(null))
}
/** Output:
 * Exception in thread "main" java.lang.NullPointerException: getName(...) must not be null
 */