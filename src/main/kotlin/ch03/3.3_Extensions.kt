package strings

fun String.lastChar(): Char = get(length - 1)

fun main() {
    println("Kotlin".lastChar())
}
/** Output:
 * n
 */