package ch05.ex1_4_2_AccessingVariablesInScope1

// 代码清单 5.11 在 lambda 中改变局部变量
fun printProblemCount(responses: Collection<String>) {
    var clientErrors = 0
    var serverErrors = 0
    responses.forEach {
        if (it.startsWith("4")) {
            clientErrors++
        } else if (it.startsWith("5")) {
            serverErrors++
        }
    }
    println("$clientErrors client errors, $serverErrors server errors")
}

fun main() {
    val response = listOf("200 OK", "418 I'm a teapot", "500 Internal Server Error")
    printProblemCount(response)
}
/** Output:
 * 1 client errors, 1 server errors
 */