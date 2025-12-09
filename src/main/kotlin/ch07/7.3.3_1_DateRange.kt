package ch07.ex3_3_1_DateRange

import java.time.LocalDate

/**
 * @author runningpig66
 * @date 2025/12/8 周一
 * @time 20:56
 * §7.3 集合与区间的约定 §7.3.3 rangeTo 的约定
 * P.191 代码清单 7.12 处理日期的区间
 *
 * .. 运算符是调用 rangeTo 函数的一个简洁方法。rangeTo 函数返回一个区间。你可以为自己的类定义这个运算符。
 * 但是，如果该类实现了 Comparable 接口，那么不需要了：你可以通过 Kotlin 标准库创建一个任意可比较元素的区间。
 * 这个库定义了可以用于任何可比较元素的 rangeTo 函数：
 * public operator fun <T : Comparable<T>> T.rangeTo(that: T): ClosedRange<T>
 * 这个函数返回一个区间，可以用来检测某些元素是否属于它。
 *
 * 作为例子，我们用 LocalDate（Java8 标准库中有定义）来构建一个日期的区间。
 */
fun main() {
    /* Kotlin 中的 `..` 与 `in` 都是“约定”（Convention）。
    1) LocalDate 是 Java 类，但它实现了 Comparable<LocalDate> 接口。
    对于任意可比较的类型，Kotlin 标准库都提供了一个扩展函数：
    operator fun <T : Comparable<T>> T.rangeTo(that: T): ClosedRange<T>
    因此，两个 LocalDate 对象之间可以使用 `a..b`，
    实际调用的就是这个 rangeTo 扩展函数，返回 ClosedRange<LocalDate>。
    2) ClosedRange<T> 接口本身定义了：
    operator fun contains(value: T): Boolean
    所以“区间.contains(x)”可以用更自然的“x in 区间”替代，
    `in` 运算符会被编译为调用 contains。
    结论：
    由于 LocalDate 可比较 ⇒ 能使用 `..` 创建日期区间；
    区间实现了 contains ⇒ 能使用 `in` 检查某日期是否属于该区间；
    所以才有 now..now.plusDays(10) 和 now.plusWeeks(1) in vacation 这样自然的语法。*/
    val now: LocalDate = LocalDate.now()
    // 创建一个从今天开始的 10 天的区间
    val vacation: ClosedRange<LocalDate> = now..now.plusDays(10)
    // 检测一个特定的日期是否属于这个区间
    println(now.plusWeeks(1) in vacation)
}
/* Output:
true
 */
