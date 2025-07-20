package ch06.ex3_2_ReadonlyAndMutableCollections

// 代码清单 6.24 使用只读集合接口与可变集合接口
fun <T> copyElements(source: Collection<T>, target: MutableCollection<T>) {
    for (item in source) {
        target.add(item)
    }
}

fun main() {
    val source: Collection<Int> = arrayListOf(3, 5, 7)
    val target: MutableCollection<Int> = arrayListOf(1)
    copyElements(source, target)
    println(target)
}
/** Output:
 * [1, 3, 5, 7]
 */