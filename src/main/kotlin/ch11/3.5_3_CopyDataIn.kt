package ch11.CopyDataIn

import ch11.note.Animal
import ch11.note.Cat

/**
 * @author runningpig66
 * @date 2026/1/19 周一
 * @time 9:56
 * Listing 11.17 A data copy function with an in-projected type parameter
 * 清单 11.17 使用 in 投影类型参数的数据复制函数
 */
fun <T> copyData(
    source: MutableList<T>,
    // Allows the destination element type to be a supertype of the source element type
    destination: MutableList<in T>
) {
    for (item in source) {
        destination.add(item)
    }
}

// 简单来说，一个完美的 copyData 函数应该允许：
// 1. 从 任何 能产出 T 的地方读取数据（Source）。
// 2. 向 任何 能接收 T 的地方写入数据（Destination）。
// 针对列表复制场景，最优的写法是将 “声明处协变（List）” 和 “使用处逆变（in）” 结合起来。
fun <T> copyDataBest(
    source: Iterable<T>, // 1. 使用 List, Iterable (自动带 out)，兼容性最强，相当于 Java 中的 Iterable<? extends T>
    destination: MutableList<in T> // 2. 使用 in T，允许写入 T 的父类集合，相当于 Java 中的 MutableList<? super T>
) {
    for (item in source) {
        destination.add(item)
    }
}

fun main() {
    // 场景 1：最基础的同类型复制
    val catsSource: List<Cat> = listOf(Cat(), Cat()) // 只读列表
    val catsDest = mutableListOf<Cat>()
    copyDataBest(catsSource, catsDest)

    // 场景 2：协变源 -> 逆变宿主 (最强场景)
    val animalsDest = mutableListOf<Animal>()
    copyDataBest(catsSource, animalsDest)

    // 场景 3：甚至复制到 Any 列表
    val anyDest = mutableListOf<Any>()
    copyDataBest(catsSource, anyDest)
}
