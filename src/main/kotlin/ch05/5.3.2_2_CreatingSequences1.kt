package ch05.ex3_2_2_CreatingSequences1

import java.io.File

// 代码清单 5.12 创建并使用父目录的序列
fun File.isInsideHiddenDirectory() =
    generateSequence(this) { it.parentFile }
        .takeWhile { it.parentFile != null } // 避免根目录
        .onEach { println("Checking: ${it.path}, isHidden: ${it.isHidden}") }
        .any { it.isHidden }

fun main() {
    val file = File("E:\\IdeaProjects\\kotlin-in-action\\.git\\hooks\\applypatch-msg.sample")
    println(file.isInsideHiddenDirectory())

    val file2 = File("E:\\IdeaProjects")
    println(file2.isInsideHiddenDirectory())
}
/**
 * Checking: E:\IdeaProjects\kotlin-in-action\.git\hooks\applypatch-msg.sample, isHidden: false
 * Checking: E:\IdeaProjects\kotlin-in-action\.git\hooks, isHidden: false
 * Checking: E:\IdeaProjects\kotlin-in-action\.git, isHidden: true
 * true
 * Checking: E:\IdeaProjects, isHidden: false
 * false
 */