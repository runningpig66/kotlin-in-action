package ch08.ex3_3_AnonymousFunctions

/**
 * @author runningpig66
 * @date 2025/12/22 周一
 * @time 23:46
 * §8.3 高阶函数中的控制流 §8.3.3 匿名函数：默认使用局部返回
 * P.228 代码清单 8.22 在匿名函数中使用 return
 * P.228 代码清单 8.23 在 filter 中使用匿名函数
 * P.229 代码清单 8.24 使用表达式体匿名函数
 *
 * 匿名函数是一种不同的用于编写传递给函数的代码块的方式。先来看一个示例：
 *
 * 在匿名函数中，不带标签的 return 表达式会从匿名函数返回，而不是从包含匿名函数的函数返回。这条规则很简单：
 * return 从最近的使用 fun 关键字声明的函数返回。lambda 表达式没有使用 fun 关键字，所以 lambda 中的 return 从最外层的函数返回。
 * 匿名函数使用了 fun，因此，在前一个例子中匿名函数是最近的符合规则的函数。所以，return 表达式从匿名函数返回，而不是从最外层的函数返回。
 */
data class Person(val name: String, val age: Int)

val people: List<Person> = listOf(Person("Alice", 29), Person("Bob", 31))

fun lookForAlice(people: List<Person>) {
    people.forEach(
        // 使用匿名函数取代 lambda 表达式
        fun(person) {
            if (person.name == "Alice") {
                // "return" 指向最近的函数：一个匿名函数
                return
            }
            println("${person.name} is not Alice")
        }
    )
}

fun main() {
    lookForAlice(people)

    // 匿名函数看起来跟普通函数很相似，除了它的名字和参数类型被省略了外。这里有另外一个例子：
    people.filter(fun(person: Person): Boolean {
        return person.age < 30
    })

    // 匿名函数和普通函数有相同的指定返回值类型的规则。
    // 代码块体匿名函数需要显式地指定返回类型，如果使用表达式函数体，就可以省略返回类型。
    people.filter(fun(person) = person.age < 30)
}
/* Output:
Bob is not Alice
 */
