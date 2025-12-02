package ch06.ex3_2_ReadonlyAndMutableCollections

/**
 * @author runningpig66
 * @date 2025/12/2 周二
 * @time 16:02
 * §6.3 集合与数组 §6.3.2 只读集合与可变集合
 * P.167 - 168 代码清单 6.24 使用只读集合接口与可变集合接口
 *
 * Kotlin 的集合设计和 Java 不同的另一项重要特质是，它把访问集合数据的接口和修改集合数据的接口分开了。
 * 这种区别存在于最基础的使用集合的接口之中：kotlin.collections.Collection. 使用这个接口，
 * 可以遍历集合中的元素、获取集合大小，判断集合中是否包含某个元素，以及执行其他从该集合中读取数据的操作。
 * 但这个接口没有任何添加或移除元素的方法。使用 kotlin.collections.MutableCollection 接口可以修改集合中的数据。
 * 它继承了普通的 kotlin.collections.Collection 接口，还提供了方法来添加和移除元素、清空集合等。
 *
 * 使用集合接口时需要牢记的一个关键点是只读集合不一定是不可变的。如果你使用的变量拥有一个只读接口类型，
 * 它可能只是同一个集合的众多引用中的一个。任何其他的引用都可能拥有一个可变接口类型。
 */
// 下面清单中的 copyElements 函数仅仅修改了 target 集合，而没有修改 source 集合。
fun <T> copyElements(source: Collection<T>, target: MutableCollection<T>) {
    // 在 source 集合中的所有元素中循环
    for (item in source) {
        // 向可变的 target 集合中添加元素
        target.add(item)
    }
}

fun main() {
    val source: Collection<Int> = arrayListOf(3, 5, 7)
    val target: MutableCollection<Int> = arrayListOf(1)
    copyElements(source, target)
    println(target)

    // 不能把只读集合类型的变量作为 target 参数传给函数，即便它的值是一个可变集合：
    val target1: Collection<Int> = arrayListOf(1)
    // Argument type mismatch: actual type is 'Collection<Int>',
    // but 'MutableCollection<Int>' was expected.
    // copyElements(source, target1) // "target" 参数错误
}
/* Output:
[1, 3, 5, 7]
 */
