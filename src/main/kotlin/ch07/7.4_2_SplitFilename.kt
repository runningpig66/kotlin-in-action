package ch07.SplitFilename

/**
 * @author runningpig66
 * @date 2025/12/9 周二
 * @time 11:50
 * §7.4 解构声明和组件函数
 * P.194 代码清单 7.14 使用解构声明返回多个值
 *
 * 解构声明主要使用场景之一，是从一个函数返回多个值。这个非常有用。
 * 如果要这样做，可以定义一个数据类来保存返回所需的值，并将它作为函数的返回类型。
 * 在调用函数后，可以用解构声明的方式，来轻松地展开它、使用其中的值。
 * 举个例子，让我们编写一个简单的函数，来将一个文件名分割成名字和扩展名。
 */
// 声明一个数据类来持有值
data class NameComponents(val name: String, val extension: String)

fun splitFileName(fullName: String): NameComponents {
    /*
    一旦在调用中使用了命名参数，那么它之后的所有参数都必须以命名形式提供。这条规则是为了保证参数传递的明确性，避免顺序混乱。
    vararg 参数在函数内部是作为一个数组处理的。当你以命名参数形式（如 delimiters =）为其传值时，必须提供一个完整的数组，而不是多个松散的值。
    当 vararg 参数是第一个参数时，可以通过位置参数便捷地传入多个值；
    但当它不是第一个参数，且前面的参数已使用命名形式时，就必须用数组方式为其命名传值。
     */
//    fullName.split('.', limit = 2)
    val result: List<String> = fullName.split(delimiters = charArrayOf('.'), limit = 2)
    // 返回一个数据类型的实例
    return NameComponents(result[0], result[1])
}

fun main() {
    // 使用解构声明来展开这个类
    val (name, ext) = splitFileName("example.kt")
    println(name)
    println(ext)
}
/* Output:
example
kt
 */
