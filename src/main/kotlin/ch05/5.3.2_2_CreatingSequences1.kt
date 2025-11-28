package ch05.ex3_2_2_CreatingSequences1

import java.io.File

/**
 * @author runningpig66
 * @date 2025/11/28 周五
 * @time 18:00
 * §5.3 情性集合操作：序列 §5.3.2 创建序列
 * P.126 代码清单 5.13 创建并使用父目录的序列
 *
 * 下面这个例子可以查询文件是否放在隐藏目录中，通过创建一个其父目录的序列并检查每个目录的属性来实现。
 */
fun File.isInsideHiddenDirectory() =
    generateSequence(this) { it.parentFile }
        .takeWhile { it.parentFile != null } // 避免根目录
        .onEach { println("Checking: ${it.path}, isHidden: ${it.isHidden}") }
        .any { it.isHidden } // 如果把 any 换成 find, 你还可以得到想要的那个目录（对象）。

fun main() {
    val file = File("E:\\IdeaProjects\\kotlin-in-action\\.git\\hooks\\applypatch-msg.sample")
    println(file.isInsideHiddenDirectory())

    val file2 = File("E:\\IdeaProjects")
    println(file2.isInsideHiddenDirectory())
}
/* Output:
Checking: E:\IdeaProjects\kotlin-in-action\.git\hooks\applypatch-msg.sample, isHidden: false
Checking: E:\IdeaProjects\kotlin-in-action\.git\hooks, isHidden: false
Checking: E:\IdeaProjects\kotlin-in-action\.git, isHidden: true
true
Checking: E:\IdeaProjects, isHidden: false
false
 */
