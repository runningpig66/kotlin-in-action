package ch07.ex1_2_2_CompoundAssignmentOperators1

/**
 * @author runningpig66
 * @date 2025/12/3 周三
 * @time 13:59
 * §7.1 重载算术运算符 §7.1.2 重载复合赋值运算符
 * P.183 代码清单
 *
 * 在一些情况下，定义 += 运算可以修改使用它的变量所引用的对象，但不会重新分配引用。
 * 将一个元素添加到可变集合，就是一个很好的例子：
 *
 * 如果你定义了一个返回值为 Unit, 名为 plusAssign 的函数，Kotlin 将会在用到 += 运算符的地方调用它。
 * 其他二元算术运算符也有命名相似的对应函数：如 minusAssign, timesAssign 等。
 *
 * 下面例子中的 += 号运算将会转换为 plusAssign 函数的调用。
 * public inline operator fun <T> MutableCollection<in T>.plusAssign(element: T)
 */
fun main() {
    val numbers = ArrayList<Int>()
    numbers += 42
    println(numbers[0])
}
/* Output:
42
 */
