package ch06.ex2_1_PrimitiveTypes

// 代码清单 P.158
fun showProgress(progress: Int) {
    val percent = progress.coerceIn(0, 100)
    println("We're ${percent}% done!")
}

fun main() {
    showProgress(145)
}
/** Output:
 * We're 100% done!
 */