package ch04.ex3_3_ClassDelegationUsingTheByKeyword

import java.util.function.IntFunction

// 代码清单 4.22 使用类委托
// 类委托：除了在 CountingSet 中显式重写的方法，其他方法都委托给 innerSet 实现。
class CountingSet<T>(
    val innerSet: MutableCollection<T> = HashSet()
) : MutableCollection<T> by innerSet {
    var objectsAdded = 0
    override fun add(element: T): Boolean {
        objectsAdded++
        return innerSet.add(element)
    }

    override fun addAll(elements: Collection<T>): Boolean {
        objectsAdded += elements.size
        return innerSet.addAll(elements)
    }

    // TODO toArray方法源码
    override fun <T : Any?> toArray(generator: IntFunction<Array<out T?>?>): Array<out T?>? {
        return super.toArray(generator)
    }
}

fun main() {
    val cset = CountingSet<Int>()
    cset.addAll(listOf(1, 1, 2))
    println("${cset.objectsAdded} objects were added, ${cset.size} remain")
}
/**
 * 3 objects were added, 2 remain
 */