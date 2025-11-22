package ch04.ex2_5_ChangingAccessorVisibility

/**
 * @author runningpig66
 * @date 2025/11/23 周日
 * @time 0:17
 * §4.2 声明一个带非默认构造方法或属性的类 §4.2.5 修改访问器的可见性
 * P.88 代码清单 4.16 声明一个具有 private setter 的属性
 *
 * 访问器的可见性默认与属性的可见性相同。但是如果需要可以通过在 get 和 set 关键字前放置可见性修饰符的方式来修改它。
 */
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
/* Output:
3
 */
