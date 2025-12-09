package ch07.ex3_1_1_IndexOperator

/**
 * @author runningpig66
 * @date 2025/12/8 周一
 * @time 18:42
 * §7.3 集合与区间的约定 §7.3.1 通过下标来访问元素："get" 和 "set"
 * P.189 代码清单 7.9 实现 get 约定
 *
 * 在 Kotlin 中，下标运算符是一个约定。使用下标运算符读取元素会被转换为 get 运算符方法的调用，并且写入元素将调用 set。
 * Map 和 MutableMap 的接口已经定义了这些方法。让我们看看如何给自定义的类添加类似的方法。
 *
 * 你只需要定义一个名为 get 的函数，并标记 operator。
 * 之后，像 p[1] 这样的表达式，其中 p 具有类型 Point，将被转换为 get 方法的调用。
 * 注意，get 的参数可以是任何类型，而不只是 Int。还可以定义具有多个参数的 get 方法。
 */
data class Point(val x: Int, val y: Int)

// 定义一个名为 "get" 的运算符函数
operator fun Point.get(index: Int): Int {
    // 根据给出的 index 返回对应的坐标
    return when (index) {
        0 -> x
        1 -> y
        else -> throw IndexOutOfBoundsException("Invalid coordinate $index")
    }
}

fun main() {
    val p = Point(10, 20)
    // 可以使用方括号来引用点的坐标：p[0] 访问 X 坐标，p[1] 访问 Y 坐标。
    // 方括号的访问会被转换为 get 函数的调用。
    println(p[1])
}
/* Output:
20
 */
