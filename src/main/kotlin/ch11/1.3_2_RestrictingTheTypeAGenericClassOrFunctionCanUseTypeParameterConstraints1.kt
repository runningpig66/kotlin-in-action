package ch11.ex3_2_RestrictingTheTypeAGenericClassOrFunctionCanUseTypeParameterConstraints1

/**
 * @author runningpig66
 * @date 2026/1/14 周三
 * @time 20:23
 * 11.1 Creating types with type arguments: Generic type parameters
 * 11.1 使用类型实参创建类型：泛型类型参数
 * 11.1.3 Restricting the type a generic class or function can use: Type parameter constraints
 * 11.1.3 限制泛型类或函数可以使用的类型：类型参数约束
 * Listing 11.3 Declaring a function with a type parameter constraint
 */
// The arguments of this function must be comparable elements.
// 你传入的这个类型 T，不能是随便一个“可比较”的东西，它必须是“可与自身类型（T）进行比较”的东西。
fun <T : Comparable<T>> max(first: T, second: T): T {
    val t = if (first.compareTo(second) > 0) first else second
    println(t)
    return if (first > second) first else second
}

fun main() {
    println(max("kotlin", "java"))
}
/* Output:
kotlin
kotlin
 */
