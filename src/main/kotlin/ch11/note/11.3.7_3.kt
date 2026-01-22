package ch11.note_3

/**
 * @author runningpig66
 * @date 2026/1/22 周四
 * @time 21:27
 * 11.3.7 Type aliases
 * 11.3.7 类型别名
 */
@JvmInline
value class ValidatedInput(val s: String)

fun save(v: ValidatedInput): Unit = TODO()

fun main() {
    val rawInput = "needs validating!"
    // Won’t compile because of the type mismatch between ValidatedInput and String
    // Error: Argument type mismatch: actual type is 'String', but 'ValidatedInput' was expected.
    //- save(rawInput)
}
