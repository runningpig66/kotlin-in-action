package ch03.ex5_2_2_RegularExpressionsAndTriplequotedStrings1

/**
 * @author runningpig66
 * @date 2025/11/20 周四
 * @time 5:40
 * 3.5 字符串和正则表达式的处理：3.5.2 正则表达式三重引号的字符串
 * P.63 代码清单 3.10 使用正则表达式解析文件路径 & P.65 代码清单
 */

fun parsePath(path: String) {
    val regex = """(.+)/(.+)\.(.+)""".toRegex()
    val matchResult = regex.matchEntire(path)
    if (matchResult != null) {
        // TODO 7.4节 后续学习解构属性
        val (directory, filename, extension) = matchResult.destructured
        println("Dir: $directory, name: $filename, ext: $extension")
    }
}

fun main() {
    parsePath("/Users/yole/kotlin-book/chapter.adoc")

    // 多行字符串 """...""" 不支持 \$ 形式的转义。要输出字面 $，旧写法为：${'$'}。
    val price0 = """${'$'}9.99"""
    // Kotlin 1.6+ 中，如果 $ 后跟非法变量名（如 $99.9，变量名不能以数字开头），会自动视为字面 $，可简写为 $99.9。
    // 但如果 $ 后跟的是合法变量名（如 $number），则会尝试做字符串模板替换，必须变量存在，否则编译报错。
    val price1 = """$9.99"""
    println(price0)
    println(price1)
}
/* Output:
Dir: /Users/yole/kotlin-book, name: chapter, ext: adoc
$9.99
$9.99
 */
