package ch05

// 代码清单 P.128
fun main() {
    // 把 lambda 作为函数式接口的实现传递
    RunnableTestImpl().postponeComputation(1000) { println(47) }

    // 把对象表达式作为函数式接口的实现传递
    RunnableTestImpl().postponeComputation(1000, object : Runnable {
        override fun run() {
            println(47)
        }
    })

    val runnable = Runnable { println(47) }
    fun handleComputation() {
        RunnableTestImpl().postponeComputation(1000, runnable)
    }

    var prefix = "$666"
    fun handleComputation1() {
        RunnableTestImpl().postponeComputation(1000) { println(prefix) }
    }
}