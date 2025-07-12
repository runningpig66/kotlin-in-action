package ch03.ex5_3_MultilineTriplequotedStrings

val kotlinLogo = """| //
                   .|//
                   .|/ \"""

fun main() {
    println(kotlinLogo.trimMargin("."))
}
/** Output:
 * | //
 * |//
 * |/ \
 */