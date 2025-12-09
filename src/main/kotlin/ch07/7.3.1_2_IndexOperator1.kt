package ch07.ex3_1_2_IndexOperator1

/**
 * @author runningpig66
 * @date 2025/12/8 周一
 * @time 19:05
 * §7.3 集合与区间的约定 §7.3.1 通过下标来访问元素："get" 和 "set"
 * P.190 代码清单 7.10 实现 set 的约定方法
 *
 * 只需要定义一个名为 set 的函数，就可以在赋值语句中使用下标运算符。
 * set 的最后一个参数用来接收赋值语句中（等号）右边的值，其他参数作为方括号内的下标。
 */
data class MutablePoint(var x: Int, var y: Int)

operator fun MutablePoint.set(index: Int, value: Int) {
    when (index) {
        x -> x = value
        1 -> y = value
        else -> throw IndexOutOfBoundsException("Invalid coordinate $index")
    }
}

fun main() {
    val p = MutablePoint(10, 20)
    // 方括号的赋值操作将会转换为 set 函数的调用
    p[1] = 42
    println(p)
}
/* Output:
MutablePoint(x=10, y=42)
 */
