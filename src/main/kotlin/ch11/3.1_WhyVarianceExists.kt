package ch11.ex1_WhyVarianceExists

/**
 * @author runningpig66
 * @date 2026/1/17 周六
 * @time 23:59
 * 11.3.1 Variance determines whether it is safe to pass an argument to a function
 * 11.3.1 型变决定了向函数传递参数是否安全
 */
fun printContents(list: List<Any>) {
    println(list.joinToString())
}

fun addAnswer(list: MutableList<Any>) {
    list.add(42)
}

fun main() {
    printContents(listOf("abc", "bac"))

    val strings: MutableList<String> = mutableListOf("abc", "bac")
    // If this line would compile ...
    //- addAnswer(strings)
    // ... you’d get an exception at run time.
    //- strings.maxBy { it.length }
    // ClassCastException: Integer cannot be cast to String
}
/* Output:
abc, bac
 */
