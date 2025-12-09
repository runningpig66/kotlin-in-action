package ch07.ex4_3_DestructuringDeclarations2

/**
 * @author runningpig66
 * @date 2025/12/9 周二
 * @time 14:35
 * §7.4 解构声明和组件函数
 * P.194 代码清单 7.15 使用解构声明来处理集合
 *
 * 如果你注意到 componentN（的扩展）函数在数组和集合上也有定义，可以进一步改进这个代码。
 * 当你在处理已知大小的集合时，这是非常有用的。一个例子就是，用 split 来返回两个元素的列表。
 *
 * 当然，不可能定义无限数量的 componentN 函数，这样这个语法就可以与任意数量的集合一起工作，但这也没用。
 * 标准库只允许使用此语法来访问一个对象的前五个元素。
 */
data class NameComponents(val name: String, val extension: String)

fun splitFileName(fullName: String): NameComponents {
    val (name, extension) = fullName.split('.', limit = 2)
    return NameComponents(name, extension)
}

fun main() {
    val (name, ext) = splitFileName("example.kt")
    println(name)
    println(ext)
}
/* Output:
example
kt
 */
