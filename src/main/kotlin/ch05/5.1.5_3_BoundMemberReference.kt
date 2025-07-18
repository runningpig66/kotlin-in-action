package ch05.ex1_5_3_BoundMemberReference

// 代码清单 P.115
data class Person(val name: String, val age: Int)

fun main() {
    //普通未绑定的成员引用
    val p = Person("Dmitry", 34)
    val personsAgeFunction = Person::age
    println(personsAgeFunction(p))

    // 绑定成员引用
    val dmitrysAgeFunction = p::age
    println(dmitrysAgeFunction())
}
/** Output:
 * 34
 * 34
 */