package ch11.ex2_1_FunctionsWithReifiedTypeParametersCanReferToActualTypeArgumentsAtRunTime

/**
 * @author runningpig66
 * @date 2026/1/16 周五
 * @time 22:04
 * Listing 11.7 Declaring a function with a reified type parameter
 * 代码清单 11.7 声明带有实化类型参数的函数
 */
// Now, this code compiles.
inline fun <reified T> isA(value: Any) = value is T
fun main() {
    println(isA<String>("abc"))
    println(isA<String>(123))
}
/* Output:
true
false
 */
