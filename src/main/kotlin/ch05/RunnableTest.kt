package ch05

/* Java */
import ch05.RunnableTest.postponeComputation

/**
 * @author runningpig66
 * @date 2025/11/29 周六
 * @time 15:01
 * §5.4 使用 Java 函数式接口 §5.4.1 把 lambda 当作参数传递给 Java 方法
 * P.128 - 129 代码清单
 *
 * [2] 处：当你显式地声明对象时，每次调用都会创建一个新的实例。[1] 处：使用 lambda 的情况不同：
 * 如果 lambda 没有访问任何来自定义它的函数的变量，相应的匿名类实例可以在多次调用之间重用。
 */
fun main() {
    // [1] 把 lambda 作为函数式接口的实现传递
    postponeComputation(1000) { println(42) } // 整个程序只会创建一个 Runnable 的实例

    // 与 [1] 完全等价的实现应该是下面这段代码中的显式 object 声明，
    // 它把 Runnable 实例存储在一个变量中，并且每次调用的时候都使用这个变量：
    val runnable = Runnable { println(42) } // 编译成全局变量；程序中仅此一个实例
    fun handleComputation() {
        // 每次 postponeComputation 调用时用的是同一个 runnable 对象
        postponeComputation(1000, runnable)
    }

    // [2] 把对象表达式作为函数式接口的实现传递
    postponeComputation(1000, object : Runnable {
        override fun run() {
            println(42)
        }
    })

    // 如果 lambda 从包围它的作用域中捕获了变量，每次调用就不再可能重用同一个实例了。
    // 这种情况下，每次调用时编译器都要创建一个新对象，其中存储着被捕捉的变量的值。
    // 例如下面这个函数，每次调用都会使用一个新的 Runnable 实例，把 id 值存储在它的字段中：
    fun handleComputation(id: String) { // lambda 会捕捉 "id" 这个变量
        // 每次 handleComputation 调用时都创建一个 Runnable 的新实例
        postponeComputation(1000) { println(id) }
    }
}
/* Output:
42
42
 */
