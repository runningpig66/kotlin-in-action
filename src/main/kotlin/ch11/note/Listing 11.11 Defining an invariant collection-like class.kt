package ch11.note

/**
 * @author runningpig66
 * @date 2026/1/18 周日
 * @time 1:19
 * Listing 11.11 Defining an invariant collection-like class
 * 清单 11.11 定义一个不变量（invariant）的类集合
 *
 * Kotlin out 是一个编译器开关。
 * 它强迫你在 A 阶段（类内部） 遵守纪律（不写 Public Set T）。
 * 从而让你在 B 阶段（外部使用） 自动获得 ? extends 的效果，而不用每次都手写。
 *
 * notes: Kotlin_Generics_Thinking_Log_Kotlin声明处协变out关键字与Java通配符的底层映射.md
 */
open class Animal

class Herd<out T : Animal>

fun feedAll(animals: Herd<Animal>) {
}

class Cat : Animal()
class Dog : Animal()

fun takeCareOfCats(cats: Herd<Cat>) {
    feedAll(cats)
}

class Herd1<out T : Animal>(vararg animals: T)

fun main() {
    val animalHerd: Herd<Animal>
    val catHerd: Herd<Cat> = Herd()
    animalHerd = catHerd
}
