package ch11.ex3_1_RestrictingTheTypeAGenericClassOrFunctionCanUseTypeParameterConstraints

/**
 * @author runningpig66
 * @date 2026/1/14 周三
 * @time 20:13
 * 11.1 Creating types with type arguments: Generic type parameters
 * 11.1 使用类型实参创建类型：泛型类型参数
 * 11.1.3 Restricting the type a generic class or function can use: Type parameter constraints
 * 11.1.3 限制泛型类或函数可以使用的类型：类型参数约束
 *
 * 一旦为类型参数 T 指定了边界，就可以将类型为 T 的值当作其上界类型的值来使用。
 * 例如，你可以调用定义在作为边界的类中的方法：
 */
// Specifies Number as the type parameter upper bound
fun <T : Number> oneHalf(value: T): Double {
    // Invokes a method defined in the Number class
    return value.toDouble() / 2.0
}

fun main() {
    println(oneHalf(3))
}
/* Output:
3
 */
