package ch13.ex4_2_ExtensionsOnPrimitiveTypes

import kotlin.time.*

/**
 * @author runningpig66
 * @date 3月16日 周一
 * @time 19:40
 * 13.4.2 Defining extensions on primitive types: Handling dates
 * 13.4.2 在基本类型上定义扩展：处理日期
 */
// Listing 13.21 Defining a date manipulation DSL
// 代码清单 13.21 定义日期操作 DSL
// 给 Int 增加扩展属性 days
val Int.days: Duration
    get() = this.toDuration(DurationUnit.DAYS)

// 给 Int 增加扩展属性 hours
val Int.hours: Duration
    get() = this.toDuration(DurationUnit.HOURS)

// fortnights (两周 / 14天)
val Int.fortnights: Duration
    get() = (this * 14).toDuration(DurationUnit.DAYS)

@OptIn(ExperimentalTime::class)
fun main() {
    val delay1 = 1.days
    val delay2 = 5.hours
    val vacation = 2.fortnights
    println("1天等于: $delay1")
    println("5小时等于: $delay2")
    println("2个双周假期等于: $vacation")

    val now = Clock.System.now()
    val yesterday = now - 1.days
    // Java 的标准写法（极其严谨，但也极其啰嗦）Instant later = now.plus(Duration.ofHours(5));
    val later = now + 5.hours // Kotlin DSL 目标写法（像英语一样流畅）
    println("now: $now")
    println("yesterday: $yesterday")
    println("later: $later")
}
/* Output:
1天等于: 1d
5小时等于: 5h
2个双周假期等于: 28d
now: 2026-03-16T12:08:50.486723900Z
yesterday: 2026-03-15T12:08:50.486723900Z
later: 2026-03-16T17:08:50.486723900Z
 */
