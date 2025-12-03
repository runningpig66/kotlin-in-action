package ch07.ex1_2_3_CompoundAssignmentOperators2

/**
 * @author runningpig66
 * @date 2025/12/3 周三
 * @time 15:15
 * §7.1 重载算术运算符 §7.1.2 重载复合赋值运算符
 * P.184 代码清单
 *
 * Kotlin 标准库支持集合的这两种方法。+ 和 - 运算符总是返回一个新的集合。
 * += 和 -= 运算符用于可变集合时，始终在一个地方修改它们；而它们用于只读集合时，会返回一个修改过的副本
 * （这意味着只有当引用只读集合的变量被声明为 var 的时候，才能使用 += 和 -=）。
 * 作为它们的运算数，可以使用单个元素，也可以使用元素类型一致的其他集合：
 *
 * 只读集合：没有 plusAssign，+= 展开为 list = list + x，也就是创建修改后的副本再重新赋值，要求 list 是 var。
 * 可变集合：+= 调用 plusAssign，就地修改，val/var 都能用。
 * + 和 - 运算符： 无论可变还是不可变集合，都返回新集合，原集合不变。
 *
 * public inline operator fun <T> MutableCollection<in T>.plusAssign(element: T)
 * public operator fun <T> Collection<T>.plus(elements: Iterable<T>): List<T>
 */
fun main() {
    val list: ArrayList<Int> = arrayListOf(1, 2)
    // += 修改 "list"
    list += 3
    // + 返回一个包含所有元素的新列表
    val newList: List<Int> = list + listOf(4, 5)
    println(list)
    println(newList)

    // += 用于只读集合时，会返回一个修改过的副本
    var readOnlyList = listOf(1, 2)
    readOnlyList += 3
    println(readOnlyList)
}
/* Output:
[1, 2, 3]
[1, 2, 3, 4, 5]
[1, 2, 3]
 */
