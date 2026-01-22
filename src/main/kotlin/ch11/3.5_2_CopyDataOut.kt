package ch11.CopyDataOut

/**
 * @author runningpig66
 * @date 2026/1/19 周一
 * @time 8:39
 * Listing 11.16 A data copy function with an out-projected type parameter
 * 清单 11.16 带有 out 投影类型参数的数据复制函数
 */
// You can add the out keyword to the type usage: no methods with T in the in position are used.
fun <T> copyData(
    source: MutableList<out T>,
    destination: MutableList<T>
) {
    for (item in source) {
        destination.add(item)
    }
}

fun <T> copyData1(
    // List<out E>: the variance of the List type parameter is specified in its declaration.
    source: List<T>,
    destination: MutableList<T>
) {
    for (item in source) {
        destination.add(item)
    }
}

fun main() {
    val list: MutableList<out Number> = mutableListOf()
    // Error: Out-projected type 'MutableList<out Number>' prohibits the use of 'fun add(element: E): Boolean'
    //- list.add(42)
}
