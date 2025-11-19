package ch03.ParsePath

/**
 * @author runningpig66
 * @date 2025/11/20 周四
 * @time 5:35
 * 3.5 字符串和正则表达式的处理：3.5.2 正则表达式三重引号的字符串
 * P.63 代码清单 3.9 使用 String 的扩展函数来解析文件路径
 */

fun parsePath(path: String) {
    val directory = path.substringBeforeLast('/')
    val fullName = path.substringAfterLast('/')

    val fileName = fullName.substringBeforeLast('.')
    val extension = fullName.substringAfterLast('.')

    println("Dir: $directory, name: $fileName, ext: $extension")
}

fun main() {
    parsePath("/Users/yole/kotlin-book/chapter.adoc")
}
/* Output:
Dir: /Users/yole/kotlin-book, name: chapter, ext: adoc
 */
