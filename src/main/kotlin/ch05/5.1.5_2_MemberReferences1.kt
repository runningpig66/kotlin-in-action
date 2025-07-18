package ch05.ex1_5_2_MemberReferences1

// 代码清单 P.115
data class Person(val name: String, val age: Int)

fun Person.isAdult() = age >= 18

fun main() {
    // 构造方法引用
    val createPerson = ::Person
    val p = createPerson("Alice", 29)
    println(p)

    // 引用扩展函数
    val predicate = Person::isAdult
    println(predicate(p))
}
/**
 * Person(name=Alice, age=29)
 * true
 */