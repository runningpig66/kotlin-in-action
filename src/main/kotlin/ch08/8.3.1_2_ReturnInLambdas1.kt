package ch08.ex3_1_2_ReturnInLambdas1

/**
 * @author runningpig66
 * @date 2025/12/22 周一
 * @time 22:21
 * §8.3 高阶函数中的控制流 §8.3.1 lambda 中的返回语句：从一个封闭的函数返回
 * P.226 代码清单 8.19 在传递给 forEach 的 lambda 中使用 return
 *
 * 使用 forEach 迭代重写这段代码安全吗？return 语句还会是一样的表现吗？是的，正如以下的代码所展示的，使用 forEach 是安全的。
 * 如果你在 lambda 中使用 return 关键字，它会从调用 lambda 的函数中返回，并不只是从 lambda 中返回。
 * 这样的 return 语句叫作非局部返回，因为它从一个比包含 return 的代码块更大的代码块中返回了。
 *
 * 为了理解这条规则背后的逻辑，想想 Java 函数中在 for 循环或者 synchronized 代码块中使用 return 关键字。
 * 显然会从函数中返回，而不是从循环或者代码块中返回。当使用以 lambda 作为参数的函数的时候 Kotlin 保留了同样的行为。
 *
 * 需要注意的是，只有在以 lambda 作为参数的函数是内联函数的时候才能从更外层的函数返回。在代码清单 8.19 中，
 * forEach 的函数体和 lambda 的函数体一起被内联了，所以在编译的时候能很容易做到从包含它的函数中返回。
 * 在一个非内联函数的 lambda 中使用 return 表达式是不允许的。一个非内联函数可以把传给它的 lambda 保存在变量中，
 * 以便在函数返回以后可以继续使用，这个时候 lambda 想要去影响函数的返回已经太晚了。
 */
data class Person(val name: String, val age: Int)

val people = listOf(Person("Alice", 29), Person("Bob", 31))

fun lookForAlice(people: List<Person>) {
    people.forEach {
        if (it.name == "Alice") {
            println("Found!")
            return // 和代码清单 8.18 中一样返回
        }
    }
    println("Alice is not found")
}

fun main() {
    lookForAlice(people)
}
/* Output:
Found!
 */
