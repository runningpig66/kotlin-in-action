package ch03.ex4_3_Infix

/**
 * @author runningpig66
 * @date 2025/11/19 周三
 * @time 5:19
 * 3.4 处理集合：可变参数、中缀调用和库的支持：3.4.3 键值对的处理：中缀调用和解构声明
 * P.60 代码清单
 *
 * 中缀调用可以与只有一个参数的函数一起使用，无论是普通的函数还是扩展函数。
 * 要允许使用中缀符号调用函数，需要使用 infix 修饰符来标记它。
 */

infix fun Any.toPair(other: Any) = Pair(this, other)

fun main() {
    // 在中经调用中，没有添加额外的分隔符，函数名称是直接放在目标对象名称和参数之间的。以下两种调用方式是等价的：
    val pair1 = 1.toPair("one") // 一般 toPair 函数的调用
    val pair2 = 1 toPair "one" // 使用中缴符号调用 toPair 函数
    println(pair1)
    println(pair2)

    // toPair 函数会返回一个 Pair 类型的对象，Pair 是 Kotlin 标准库中的类，不出所料，它会用来表示一对元素。
    // 可以直接用 Pair 的内容来初始化两个变量：这个功能称为解构声明。
    val (number, name) = 1 toPair "one"
    println("number is $number and name is $name")
}
/* Output:
(1, one)
(1, one)
number is 1 and name is one
 */
