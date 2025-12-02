package ch06.ex2_1_PrimitiveTypes

/**
 * @author runningpig66
 * @date 2025/12/2 周二
 * @time 10:58
 * §6.2 基本数据类型和其他基本类型 §6.2.1 基本数据类型：Int, Boolean, 及其他
 * P.158 代码清单
 *
 * Kotlin 并不区分基本数据类型和包装类型，你使用的永远是同一个类型 (比如：Int):
 *
 * 这样很方便。此外，你还能对一个数字类型的值调用方法。
 * 例如下面这段代码中，使用了标准库的函数 coerceIn 来把值限制在特定范围内：
 */
fun showProgress(progress: Int) {
    val i: Int = 1
    val list: List<Int> = listOf(1, 2, 3)

    val percent: Int = progress.coerceIn(0, 100)
    println("We're ${percent}% done!")
}

fun main() {
    showProgress(145)
}
/* Output:
We're 100% done!
 */
