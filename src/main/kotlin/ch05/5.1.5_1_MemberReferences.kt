package ch05.ex1_5_1_MemberReferences

// 代码清单 P.114
fun salute() = println("Salute!")

fun main() {
    run(::salute)
}
/**
 * Salute!
 */