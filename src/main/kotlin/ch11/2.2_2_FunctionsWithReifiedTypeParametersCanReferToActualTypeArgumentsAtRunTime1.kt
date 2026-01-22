package ch11.ex2_2_FunctionsWithReifiedTypeParametersCanReferToActualTypeArgumentsAtRunTime1

import java.io.Serializable

/**
 * @author runningpig66
 * @date 2026/1/16 周五
 * @time 22:54
 * Listing 11.8 Using the filterIsInstance standard library function
 * 清单 11.8 使用 filterIsInstance 标准库函数
 *
 * notes: Kotlin_Reified_实化原理_基于内联的字节码指令重写与常量替换.md
 */
// 11.2.4 Declaring accessors with reified type parameters
// 11.2.4 使用实构化类型参数声明访问器
inline val <reified T> T.canonical: String
    get() = T::class.java.canonicalName

fun main() {
    val items = listOf("one", 2, "three")
    val items1: List<Comparable<*>> = listOf("one", 2, "three")
    val items2: List<Serializable> = listOf("one", 2, "three")
    val items3: List<Any?> = listOf("one", 2, "three")
    val items4: List<Any> = listOf("one", 2, "three")
    println(items.filterIsInstance<String>())

    println(items.canonical)
    println(listOf(1, 2, 3).canonical)
    println(1.canonical)
}
/* Output:
[one, three]
java.util.List
java.util.List
java.lang.Integer
 */

// TODO filterIsInstance() 函数
// public inline fun <reified R> Iterable<*>.filterIsInstance(): List<@kotlin.internal.NoInfer R> {
//     return filterIsInstanceTo(ArrayList<R>())
// }

// public inline fun <reified R, C : MutableCollection<in R>> Iterable<*>.filterIsInstanceTo(destination: C): C {
//     for (element in this) if (element is R) destination.add(element)
//     return destination
// }
