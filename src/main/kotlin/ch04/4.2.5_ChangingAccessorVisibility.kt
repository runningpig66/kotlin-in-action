package ch04.ex2_5_ChangingAccessorVisibility

// 代码清单 4.16 声明一个具有 private setter 的属性
class LengthCounter {
    // 不能在类外部修改这个属性
    var counter: Int = 0
        private set

    fun addWord(word: String) {
        counter += word.length
    }
}

fun main() {
    val lengthCounter = LengthCounter()
    lengthCounter.addWord("Hi!")
    println(lengthCounter.counter)
}
/** Output:
 * 3
 */