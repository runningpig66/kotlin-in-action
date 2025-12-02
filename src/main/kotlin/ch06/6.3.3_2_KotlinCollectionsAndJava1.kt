package ch06.ex3_3_2_KotlinCollectionsAndJava1

import ch06.CollectionUtils

/**
 * @author runningpig66
 * @date 2025/12/2 周二
 * @time 17:21
 * §6.3 集合与数组 §6.3.3 Kotlin 集合和 Java
 * P.170 代码清单
 *
 * 当你需要调用一个 Java 方法并把集合作为实参传给它时，可以直接这样做，不需要任何额外的步骤。
 * 例如，你有一个使用 java.util.Collection 做形参的 Java 方法，
 * 可以把任意 Collection 或 MutableCollection 的值作为实参传递给这个形参。
 *
 * 这对集合的可变性有重要影响。因为 Java 并不会区分只读集合与可变集合，即使 Kotlin 中把集合声明成只读的，
 * Java 代码也能够修改这个集合。Kotlin 编译器不能完全地分析 Java 代码到底对集合做了什么，
 * 因此 Kotlin 无法拒绝向可以修改集合的 Java 代码传递只读 Collection. 因此，如果你写了一个 Kotlin 函数，
 * 使用了集合并传递给了 Java, 你有责任使用正确的参数类型，这取决于你调用的 Java 代码是否会修改集合。
 *
 * 留意此注意事项也适用于包含非空类型元素的集合类。如果你向 Java 方法传递了这样的集合，
 * 该方法就可能在其中写入 null 值：Kotlin 没有办法在不影响性能的情况下，禁止它的发生，或者察觉到已经发生的改变。
 * 因此，当你向可以修改集合的 Java 代码传递集合的时候，你需要采取特别的预防措施，
 * 来确保 Kotlin 类型正确地反映出集合上所有可能的修改。
 */
fun printInUppercase(list: List<String>) { // 声明只读的参数
    // 调用可以修改集合的 Java 函数
    println(CollectionUtils.uppercaseAll(list))
    // 打印被修改过的集合
    println(list.first())
}

fun main() {
    val list: List<String> = listOf("a", "b", "c")
    printInUppercase(list)
}
/* Output:
[A, B, C]
A
 */
