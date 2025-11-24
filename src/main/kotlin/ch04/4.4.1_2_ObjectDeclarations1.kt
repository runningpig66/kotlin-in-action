package ch04.ex4_1_2_ObjectDeclarations1

/**
 * @author runningpig66
 * @date 2025/11/24 周一
 * @time 0:31
 * §4.4 "object" 关键字：将声明一个类与创建一个实例结合起来 §4.4.1 对象声明：创建单例易如反掌
 * P.97 代码清单 4.24 使用嵌套类实现 Comparator
 *
 * 同样可以在类中声明对象。这样的对象同样只有一个单一实例；它们在每个容器类的实例中并不具有不同的实例。
 * 例如，在类中放置一个用来比较特定对象的比较器是合乎逻辑的。
 */
data class Person(val name: String) {
    object NameComparator : Comparator<Person> {
        override fun compare(p1: Person, p2: Person): Int {
            return p1.name.compareTo(p2.name)
        }
    }
}

fun main() {
    val persons = listOf(Person("Bob"), Person("Alice"))
    println(persons.sortedWith(Person.NameComparator))
}
/* Output:
[Person(name=Alice), Person(name=Bob)]
 */
