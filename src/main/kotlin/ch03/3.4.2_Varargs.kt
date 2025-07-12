package ch03.ex4_2_Varargs

fun main(args: Array<String>) {
    val list = listOf("args: ", *args)
    println(list)
}
/** Output:
 * [args: , arg1, arg2, arg3]
 */