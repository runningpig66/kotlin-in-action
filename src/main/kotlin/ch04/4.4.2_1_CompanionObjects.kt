package ch04.ex4_2_1_CompanionObjects

// 代码清单 P.98
class A {
    companion object {
        fun bar() {
            println("Companion object called")
        }
    }
}

fun main() {
    A.bar()
}
/**
 * Companion object called
 */