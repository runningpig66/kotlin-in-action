package ch08.ex2_5_UsingInlinedLambdasForResourceManagement

import java.io.BufferedReader
import java.io.FileReader

/**
 * @author runningpig66
 * @date 2025/12/21 周日
 * @time 23:09
 * §8.2 内联函数：消除 lambda 带来的运行时开销 §8.2.5 使用内联 lambda 管理资源
 * P.224 代码清单 8.17 使用 use 函数作资源管理
 *
 * use 函数是一个扩展函数，被用来操作可关闭的资源，它接收一个 lambda 作为参数。这个方法调用 lambda 并且确保资源被关闭，
 * 无论 lambda 正常执行还是抛出了异常。当然，use 函数是内联函数，所以使用它并不会引发任何性能开销。
 * public inline fun <T : Closeable?, R> T.use(block: (T) -> R): R
 */
fun readFirstLineFromFile(path: String): String {
    // 构建 BufferedReader，调用 "use" 函数，传递一个 lambda 执行文件操作
    BufferedReader(FileReader(path)).use { br: BufferedReader ->
        // 从函数中返回文件的一行
        return br.readLine()
    }
}
