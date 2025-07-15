package ch04.ex4_1_1_ObjectDeclarations

import java.io.File

// 代码清单 4.23 使用对象来实现 Comparator
object CaseInsensitiveFileComparator : Comparator<File> {
    override fun compare(file1: File, file2: File): Int {
        return file1.path.compareTo(file2.path, ignoreCase = true)
    }
}

fun main() {
    println(CaseInsensitiveFileComparator.compare(File("/User"), File("/user")))
    val files = listOf(File("/Z"), File("/a"))
    println(files.sortedWith(CaseInsensitiveFileComparator))
}
/** Output:
 * 0
 * [\a, \Z]
 */