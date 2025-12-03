package ch07.ex1_1_4_BinaryOperators3

/**
 * @author runningpig66
 * @date 2025/12/3 周三
 * @time 11:38
 * §7.1 重载算术运算符 §7.1.1 重载二元算术运算
 * P.182 代码清单 7.4 定义一个返回结果不同的运算符
 *
 * 运算符函数的返回类型也可以不同于任一运算数类型。例如，可以定义一个运算符，通过多次重复单个字符来创建字符串。
 * 这个运算符，接收一个 Char 作为左值，Int 作为右值，然后返回一个 String 类型。这样的运算数和结果类型的组合是允许的。
 *
 * 注意，和普通的函数一样，可以重载 operator 函数：可以定义多个同名的，但参数类型不同的方法。
 *
 * public actual fun CharSequence.repeat(n: Int): String
 */
operator fun Char.times(count: Int): String {
    return toString().repeat(count)
}

fun main() {
    println('a' * 3)
}
/* Output:
aaa
 */
