package ch11.ex1_2_LimitationsToFindingTypeInformationOfAGenericClassAtRunTimeTypeChecksAndCasts1

/**
 * @author runningpig66
 * @date 2026/1/16 周五
 * @time 20:33
 * Listing 11.6 Using a type check with a known type argument
 * 代码清单 11.6 对已知类型实参使用类型检查
 */
// Because the element type Int is known at compile time ...
fun printSum(c: Collection<Int>) {
    when (c) {
        // ... these checks are legitimate.
        is List<Int> -> println("List sum: ${c.sum()}")
        is Set<Int> -> println("Set sum: ${c.sum()}")
    }
}

fun main() {
    printSum(listOf(1, 2, 3))
    printSum(setOf(3, 4, 5))
}
/* Output:
List sum: 6
Set sum: 12
 */
