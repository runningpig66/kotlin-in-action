package ch05.ex1_5_2_MemberReferences1

/**
 * @author runningpig66
 * @date 2025/11/28 周五
 * @time 0:20
 * §5.1 Lambda 表达式和成员引用 §5.1.5 成员引用
 * P.115 代码清单 构造方法引用 & 扩展函数引用
 */
data class Person(val name: String, val age: Int)

fun Person.isAdult() = age >= 21
fun main() {
    // 构造方法引用：创建 "Person" 实例的动作被保存成了值
    val createPerson: (String, Int) -> Person = ::Person
    val p = createPerson("Alice", 29)
    println(p)

    // 引用扩展函数：尽管 isAdult 不是 Person 类的成员，还是可以通过引用访问它，
    // 这和访问实例的成员没什么两样：person.isAdult().
    val predicate: (Person) -> Boolean = Person::isAdult
    println(predicate(p))
}
/* Output:
Person(name=Alice, age=29)
true
 */
