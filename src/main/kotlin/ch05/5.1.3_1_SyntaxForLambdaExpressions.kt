package ch05

import ch05.ex1_2_LambdasAndCollections.Person

/**
 * @author runningpig66
 * @date 2025/11/25 周二
 * @time 17:08
 * §5.1 Lambda 表达式和成员引用 §5.1.3 Lambda 表达式的语法
 * P.108 代码清单
 *
 * 如前所述，一个 lambda 把一小段行为进行编码，你能把它当作值到处传递。
 * 它可以被独立地声明并存储到一个变量中。但是更常见的还是直接声明它并传递给函数。
 */
fun main() {
    // 定义一个接收两个 Int 并返回 Int 的 lambda 并存储到 sum 变量中,
    // 编译器推断 sum 的类型为 (Int, Int) -> Int
    // Kotlin 的 lambda 表达式始终用花括号包围。注意实参并没有用括号括起来。箭头把实参列表和 lambda 的函数体隔开。
    // 可以把 lambda 表达式存储在一个变量中，把这个变量当作普通函数对待（即通过相应实参调用它）：
    val sum = { x: Int, y: Int -> x + y }
    println(sum(1, 2)); /* 注意：这里特意在行末写分号，是为了“强制结束这一条语句”。
                           如果不写分号，下一行以 { 开头的表达式会被当作 println 的尾随 lambda,
                           两行会被解析成一条形如 println(sum(1, 2)) { ... } 的语句，
                           从而触发类型推断错误，所以这里需要显式终止语句。*/

    // 如果你乐意，还可以直接调用 lambda 表达式：
    { println(42) }()

    // 但是这样的语法毫无可读性，也没有什么意义（它等价于直接执行 lambda 函数体中的代码）。
    // 如果你确实需要把一小段代码封闭在一个代码块中，可以使用库函数 run 来执行传给它的 lambda:
    run { println(42) } // 运行 lambda 中的代码

    // 我们回到代码清单 5.4 中寻找列表中年龄最大的人的代码：
    val people = listOf(Person("Alice", 29), Person("Bob", 31))
    println(people.maxBy { it.age })
    // 如果不用任何简明语法来重写这个例子，你会得到下面的代码：
    people.maxBy({ p: Person -> p.age })
    // 如果 lambda 表达式是函数调用的最后一个实参，它可以放到括号的外边：
    people.maxBy { p: Person -> p.age }
    // 当 lambda 是函数唯一的实参时，还可以去掉调用代码中的空括号对：
    people.maxBy { p: Person -> p.age }
}
/* Output:
3
42
42
Person(name=Bob, age=31)
 */
