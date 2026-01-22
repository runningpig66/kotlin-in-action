package ch11

/**
 * @author runningpig66
 * @date 2026/1/14 周三
 * @time 16:59
 * 11.1 Creating types with type arguments: Generic type parameters
 * 11.1 使用类型实参创建类型：泛型类型参数
 * 11.1.1 Functions and properties that work with generic types
 * 11.1.1 处理泛型类型的函数和属性
 * Listing 11.2 Calling a generic higher-order function
 *
 * inline fun <T> Iterable<T>.filter(predicate: (T) -> Boolean): List<T>
 */
// This generic extension property can be called on a list of any kind.
val <T> List<T>.penultimate: T
    get() = this[size - 2]

fun main() {
    val authors = listOf("Sveta", "Seb", "Roman", "Dima")
    val readers = mutableListOf<String>("Seb", "Hadi")
    println(readers.filter { it !in authors })

    // The type parameter T is inferred to be Int in this invocation.
    println(listOf(1, 2, 3, 4).penultimate)
}
/* Output:
[Hadi]
3
 */
