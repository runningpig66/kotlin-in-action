package ch08.ex1_2_1_CallingFunctionsPassedAsArguments

/**
 * @author runningpig66
 * @date 2025/12/13 周六
 * @time 21:17
 * §8.1 声明高阶函数 §8.1.2 调用作为参数的函数
 * P.209 代码清单 8.1 定义一个简单高阶函数
 */
fun twoAndThree(operation: (Int, Int) -> Int) { // 定义一个函数类型的参数
    val result = operation(2, 3) // 调用函数类型的参数
    println("The result is $result")
}

fun main() {
    twoAndThree { a, b -> a + b }
    twoAndThree { a, b -> a * b }
}
/* Output:
The result is 5
The result is 6
 */
