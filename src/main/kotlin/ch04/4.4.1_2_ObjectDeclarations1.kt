package ch04.ex4_1_2_ObjectDeclarations1

// 代码清单 4.24 使用嵌套类实现 Comparator
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
/**
 * [Person(name=Alice), Person(name=Bob)]
 */