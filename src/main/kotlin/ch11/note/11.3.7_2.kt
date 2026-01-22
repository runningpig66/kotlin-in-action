package ch11.note_2

/**
 * @author runningpig66
 * @date 2026/1/22 周四
 * @time 21:21
 * 11.3.7 Type aliases
 * 11.3.7 类型别名
 */
typealias ValidatedInput = String

fun save(v: ValidatedInput): Unit = TODO()

fun main() {
    val rawInput = "needs validating!"
    // Type aliases introduce no extra compile-time guarantees.
    save(rawInput)
}
