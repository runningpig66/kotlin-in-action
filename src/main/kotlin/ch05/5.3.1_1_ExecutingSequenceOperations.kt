package ch05.ex3_1_1_ExecutingSequenceOperations

// 代码清单 P.123
fun main() {
    listOf(1, 2, 3, 4).asSequence()
        .map { print("map($it) "); it * it }
        .filter { print("filter($it) "); it % 2 == 0 }
}