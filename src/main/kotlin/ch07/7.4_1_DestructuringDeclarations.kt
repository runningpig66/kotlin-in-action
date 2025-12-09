package ch07.ex4_1_DestructuringDeclarations

/**
 * @author runningpig66
 * @date 2025/12/9 周二
 * @time 11:21
 * §7.4 解构声明和组件函数
 * P.193 代码清单
 *
 * 解构声明：这个功能允许你展开单个复合值，并使用它来初始化多个单独的变量。
 * 事实上，解构声明再次用到了约定的原理。要在解构声明中初始化每个变量，
 * 将调用名为 componentN 的函数，其中 N 是声明中变量的位置。
 */
// 对于数据类，编译器为每个在主构造方法中声明的属性生成一个 componentN 函数。
data class Point(val x: Int, val y: Int)

fun main() {
    val p = Point(10, 20)
    // 声明变量 x, y, 然后用 p 的组件来初始化
    val (x, y) = p // 解构声明被转换为 componentN 函数的调用
    println(x)
    println(y)
}
/* Output:
10
20
 */
