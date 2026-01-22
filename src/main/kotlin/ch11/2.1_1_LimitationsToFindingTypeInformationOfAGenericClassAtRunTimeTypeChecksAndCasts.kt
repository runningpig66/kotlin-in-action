package ch11.ex1_1_LimitationsToFindingTypeInformationOfAGenericClassAtRunTimeTypeChecksAndCasts

/**
 * @author runningpig66
 * @date 2026/1/16 周五
 * @time 16:39
 * Listing 11.5 Using a type cast with a generic type
 * 清单 11.5 对泛型类型使用类型转换
 */
@Suppress("UNCHECKED_CAST")
fun printSum(c: Collection<*>) {
    val intList = c as? List<Int>
        ?: throw IllegalArgumentException("List is expected")
    println(intList.sum())
}

fun main() {
    // With lists, everything works as expected.
    printSum(listOf(1, 2, 3))

    // The set isn’t a list, so an exception is thrown.
    //- printSum(setOf(3, 4, 5)) // IllegalArgumentException: List is expected

    // The cast succeeds, but since strings cannot be summed, another exception is thrown later.
    //- printSum(listOf("a", "b", "c")) // ClassCastException: String cannot be cast to Number
}
/* Output:
6
 */
