package ch04.ex3_3_ClassDelegationUsingTheByKeyword

/**
 * @author runningpig66
 * @date 2025/11/23 周日
 * @time 2:19
 * §4.3 编译器生成的方法：数据类和类委托 §4.3.3 类委托：使用 "by" 关键字
 * P.94 代码清单 4.22 使用类委托
 *
 * 无论什么时候实现一个接口，你都可以使用 by 关键字将接口的实现委托到另一个对象。
 * 当你需要修改某些方法的行为时，你可以重写它们，这样你的方法就会被调用而不是使用委托生成的方法。
 * 可以保留感到满意的委托给内部的实例中的默认实现。
 *
 * 让我们来看看怎样使用这种技术来实现一个集合，它可以计算向它添加元素的尝试次数。
 * 例如，你在执行某种去重操作，可以使用这样的集合，通过比较添加元素的尝试次数和集合的最终大小来评判这种处理的效率。
 * 除了在 CountingSet 中显式重写的方法，其他方法都委托给 innerSet 实现。
 */
class CountingSet<T>(val innerSet: MutableCollection<T> = HashSet()) :
    MutableCollection<T> by innerSet { // 将 MutableCollection 的实现委托给 innerSet
    var objectsAdded = 0

    // 不使用委托，提供一个不同的实现
    // 通过重写 add 和 addAll 方法来计数，并将 MutableCollection 接口剩下的实现委托给被你包装的容器。
    override fun add(element: T): Boolean {
        objectsAdded++
        return innerSet.add(element)
    }

    override fun addAll(elements: Collection<T>): Boolean {
        objectsAdded += elements.size
        return innerSet.addAll(elements)
    }
}

fun main() {
    val cset = CountingSet<Int>()
    cset.addAll(listOf(1, 2, 3))
    println("${cset.objectsAdded} objects were added, ${cset.size} remain")
}
/* Output:
3 objects were added, 3 remain
 */
