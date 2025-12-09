package ch07.ex3_4_IteratorConvention

import java.time.LocalDate

/**
 * @author runningpig66
 * @date 2025/12/8 周一
 * @time 22:06
 * §7.3 集合与区间的约定 §7.3.4 在 "for" 循环中使用 "iterator" 的约定
 * P.192 代码清单 7.13 实现日期区间的迭代器
 *
 * 正如我们在第 2 章中讨论的，在 Kotlin 中，for 循环中也可以使用 in 运算符，和做区间检查一样。
 * 但是在这种情况下它的含义是不同的：它被用来执行迭代。
 * 这意味着一个诸如 for(x in list) {...} 将被转换成 list.iterator() 的调用，
 * 然后就像在 Java 中一样，在它上面重复调用 hasNext 和 next 方法。
 *
 * 请注意，在 Kotlin 中，这也是一种约定，这意味着 iterator 方法可以被定义为扩展函数。这就解释了为什么可以遍历一个常规的 Java 字符串；
 * 标准库已经为 CharSequence 定义了一个扩展函数 iterator，而它是 String 的父类。
 * public operator fun CharSequence.iterator(): CharIterator
 * >>> for (c in "abc") {} // 这个库函数让迭代字符串成为可能
 *
 * 请注意如何在自定义区间类型上定义 iterator 方法：使用 LocalDate 作为类型参数。
 * 如上一节所示，rangeTo 库函数返回一个 ClosedRange 的实例，
 * 并且 ClosedRange<LocalDate> 的 iterator 扩展允许在 for 循环中使用区间的实例。
 */
operator fun ClosedRange<LocalDate>.iterator(): Iterator<LocalDate> =
    // 这个对象实现了遍历 LocalDate 元素的 Iterator
    object : Iterator<LocalDate> {
        var current: LocalDate = start
        override fun hasNext(): Boolean {
            // 注意，这里日期用到了 compareTo 约定
            return current <= endInclusive
        }

        /* When mutating state, use apply; When transforming value, avoid apply.
        注意：apply 不会帮你改变对象，它只负责把接收者 this 原样返回，真正改变要靠你在 lambda 里写的副作用，
        即你在 lambda 里放进去的函数。如果那个函数返回新对象，那 apply 就什么都没改变。
        而且对 immutable 不可变类型（LocalDate、String 等尤其要警惕）根本改不了它，只能换一个新的对象。
        LocalDate 是不可变类，plusDays() 返回新 LocalDate，不会修改 current 本身，
        因此不能用 apply 来期望“修改 this”，要显式赋值。
        */
        // **在修改前返回当前日期作为结果**
        override fun next(): LocalDate = current.apply {
            /*
            `apply` 始终返回其接收者对象（`this`，即调用前的 `current`）。
            由于 `LocalDate` 是不可变的，`plusDays(1)` 会返回一个新日期对象，
            因此必须在 lambda 内通过赋值 (`current = ...`) 来更新 `current` 的引用。
            这正是 `next()` 方法所需的“返回当前值，并指向下一个值”的行为。
             */
            current = plusDays(1) // 把当前日期增加一天。关键：让 `current` 指向新的日期对象
        }
    }

fun main() {
    val newYear: LocalDate = LocalDate.ofYearDay(2017, 1)
    val daysOff: ClosedRange<LocalDate> = newYear.minusDays(1)..newYear
    // 对应的 iterator 函数实现后，遍历 daysOff
    for (dayOff: LocalDate in daysOff) {
        println(dayOff)
    }
}
/* Output:
2016-12-31
2017-01-01
 */
