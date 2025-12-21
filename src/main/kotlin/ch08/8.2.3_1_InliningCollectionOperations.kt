package ch08.ex2_3_1_InliningCollectionOperations

/**
 * @author runningpig66
 * @date 2025/12/21 周日
 * @time 21:32
 * §8.2 内联函数：消除 lambda 带来的运行时开销 §8.2.3 内联集合操作
 * P.222 代码清单 8.14 使用 lambda 过滤一个集合
 * P.222 代码清单 8.15 手动过滤一个集合
 *
 * 我们来仔细看一看 Kotlin 标准库中操作集合的函数的性能。大部分标准库中的集合函数都带有 lambda 参数。
 * 相比于使用标准库函数，直接实现这些操作不是更高效吗？例如，让我们来比较以下两个代码清单中用来过滤一个人员列表的方式：
 *
 * 在 Kotlin 中，filter 函数被声明为内联函数。这意味着 filter 函数，以及传递给它的 lambda 的字节码会被一起内联到 filter 被调用的地方。
 * 最终，第一种实现所产生的字节码和第二种实现所产生的字节码大致是一样的。
 * 你可以很安全地使用符合语言习惯的集合操作，Kotlin 对内联函数的支持让你不必担心性能的问题。
 *
 * 编译后的大致字节码：
 * public static final void main() {
 *    Iterable filter = (Iterable)people;
 *    Collection destination = (Collection)(new ArrayList());
 *    for(Object element : filter) {
 *       Person it = (Person)element;
 *       if (it.getAge() < 30) {
 *          destination.add(element);
 *       }
 *    }
 *    System.out.println(destination);
 * }
 */
data class Person(val name: String, val age: Int)

val people = listOf(Person("Alice", 29), Person("Bob", 31))

fun main() {
    println(people.filter { it.age < 30 })

    // 前面的代码不用 lambda 表达式也可以实现，代码如下：
    val result = mutableListOf<Person>()
    for (person in people) {
        if (person.age < 30) {
            result.add(person)
        }
    }
    println(result)
}
/* Output:
[Person(name=Alice, age=29)]
[Person(name=Alice, age=29)]
 */
