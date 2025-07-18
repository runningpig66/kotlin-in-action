package ch05.ex3_1_2_ExecutingSequenceOperations1

// 代码清单 P.123
fun main() {
    listOf(1, 2, 3, 4).asSequence()
        .map { print("map($it) "); it * it }
        .filter { print("filter($it) "); it % 2 == 0 }
        .toList()
}
/** Output:
 * map(1) filter(1) map(2) filter(4) map(3) filter(9) map(4) filter(16)
 */