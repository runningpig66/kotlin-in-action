package ch05.ex1_5_3_BoundMemberReference

/**
 * @author runningpig66
 * @date 2025/11/28 周五
 * @time 0:34
 * §5.1 Lambda 表达式和成员引用 §5.1.5 成员引用
 * P.115 代码清单 绑定成员引用
 */
data class Person(val name: String, val age: Int)

fun main() {
    // 普通未绑定的成员引用
    val p = Person("Dmitry", 34)
    val personsAgeFunction: (Person) -> Int = Person::age
    println(personsAgeFunction(p))

    // 绑定成员引用
    val dmitrysAgeFunction: () -> Int = p::age // 在 Kotlin 1.1 中可以使用绑定成员引用
    println(dmitrysAgeFunction())
}
/* Output:
34
34
 */
