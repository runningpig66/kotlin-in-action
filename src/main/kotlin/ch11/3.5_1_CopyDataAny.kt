package ch11.CopyDataAny

/**
 * @author runningpig66
 * @date 2026/1/19 周一
 * @time 8:25
 * Listing 11.15 A data copy function with two type parameters
 * 列表 11.15 带有两个类型参数的数据复制函数
 */
// The source’s element type should be a subtype of the destination’s element type.
fun <T : R, R> copyData(
    source: MutableList<T>,
    destination: MutableList<R>
) {
    for (item in source) {
        destination.add(item)
    }
}

fun main() {
    val ints = mutableListOf(1, 2, 3)
    val anyItems = mutableListOf<Any>()
    // You can call this function because Int is a subtype of Any.
    copyData(ints, anyItems)
    println(anyItems)
}
/* Output:
[1, 2, 3]
 */
