package ch06.ex3_5_3_Arrays2

// 代码清单 6.31 向 vararg 方法传递集合
fun main() {
    val strings = listOf("a", "b", "c")
    println("%s/%s/%s".format(*strings.toTypedArray()))
}
/** Output:
 * a/b/c
 */