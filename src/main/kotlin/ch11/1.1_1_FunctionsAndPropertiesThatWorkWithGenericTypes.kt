package ch11.ex1_1_FunctionsAndPropertiesThatWorkWithGenericTypes

/**
 * @author runningpig66
 * @date 2026/1/14 周三
 * @time 16:45
 * 11.1 Creating types with type arguments: Generic type parameters
 * 11.1 使用类型实参创建类型：泛型类型参数
 * 11.1.1 Functions and properties that work with generic types
 * 11.1.1 处理泛型类型的函数和属性
 * Listing 11.1 Calling a generic function
 *
 * fun <T> List<T>.slice(indices: IntRange): List<T>
 */
fun main() {
    val letters = ('a'..'z').toList()
    println(letters.slice<Char>(0..2)) // Specifies the type argument explicitly
    println(letters.slice(10..13)) // The compiler infers that T is Char here.
}
/* Output:
[a, b, c]
[k, l, m, n]
 */
