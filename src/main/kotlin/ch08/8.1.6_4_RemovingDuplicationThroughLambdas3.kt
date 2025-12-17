package ch08.ex1_6_4_RemovingDuplicationThroughLambdas3

/**
 * @author runningpig66
 * @date 2025/12/17 周三
 * @time 23:03
 * §8.1 声明高阶函数 §8.1.6 通过 lambda 去除重复代码
 * P.218 代码清单 8.12 用一个高阶函数去除重复代码
 *
 * 现在已经无法再用一个简单的参数表示不同的平台了。你可能还需要使用更加复杂的条件查询日志，
 * 比如“来自 iOS 平台对注册页面的访问的平均时间是多少？”Lambda 可以帮上忙。可以用函数类型将需要的条件抽取到一个参数中。
 *
 * 函数类型可以帮助去除重复代码。如果你禁不住复制粘贴了一段代码，那么很可能这段重复代码是可以避免的。
 * 使用 lambda，不仅可以抽取重复的数据，也可以抽取重复的行为。
 */
data class SiteVisit(
    val path: String,
    val duration: Double,
    val os: OS
)

enum class OS {
    WINDOWS, LINUX, MAC, IOS, ANDROID
}

val log = listOf(
    SiteVisit("/", 34.0, OS.WINDOWS),
    SiteVisit("/", 22.0, OS.MAC),
    SiteVisit("/login", 12.0, OS.WINDOWS),
    SiteVisit("/signup", 8.0, OS.IOS),
    SiteVisit("/", 16.3, OS.ANDROID)
)

// 注意：一些广为人知的设计模式可以用函数类型和 lambda 表达式进行简化，比如策略模式。
// 没有 lambda 表达式的情况下，你需要声明一个接口，并为每一种可能的策略提供实现类。
// 使用函数类型，可以用一个通用的函数类型来描述策略，然后传递不同的 lambda 表达式作为不同的策略。
fun List<SiteVisit>.averageDurationFor(predicate: (SiteVisit) -> Boolean) =
    this.filter(predicate).map(SiteVisit::duration).average()

fun main() {
    println(log.averageDurationFor {
        it.os in setOf(OS.ANDROID, OS.IOS)
    })
    println(log.averageDurationFor {
        it.os == OS.IOS && it.path == "/signup"
    })
}
/* Output:
12.15
8.0
 */
