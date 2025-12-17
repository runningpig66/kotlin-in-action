package ch08.ex1_1_FunctionTypes

/**
 * @author runningpig66
 * @date 2025/12/13 周六
 * @time 21:00
 * §8.1 声明高阶函数 §8.1.1 函数类型
 * P.209 代码清单 函数类型的参数名
 */
fun performRequest(
    url: String,
    // 函数类型的参数现在有了名字
    callback: (code: Int, content: String) -> Unit
) {
    /*...*/
}

fun main() {
    val url = "http://kotl.in"
    // 可以使用 API 中提供的参数名字作为 lambda 参数的名字……
    performRequest(url) { code, content -> /*...*/ }
    // ……或者你可以改变参数的名字
    performRequest(url) { code, page -> /*...*/ }
}
