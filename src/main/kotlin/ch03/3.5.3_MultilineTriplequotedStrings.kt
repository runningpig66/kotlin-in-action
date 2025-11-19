package ch03.ex5_3_MultilineTriplequotedStrings

/**
 * @author runningpig66
 * @date 2025/11/20 周四
 * @time 5:54
 * 3.5 字符串和正则表达式的处理：3.5.3 多行三重引号的字符串
 * P.65 代码清单
 *
 * 多行字符串包含三重引号之间的所有字符，包括用于格式化代码的缩进。如果要更好地表示这样的字符串，
 * 可以去掉缩进（左边距）。为此，可以向字符串内容添加前缀，标记边距的结尾，
 * 然后调用 trimMargin 来删除每行中的前缀和前面的空格。在本例中，就是使用了点来作为前缀。
 */
val kotlinLogo: String = """| //
                   .|//
                   .|/ \"""

fun main() {
    println(kotlinLogo.trimMargin("."))
}
/* Output:
| //
|//
|/ \
 */
