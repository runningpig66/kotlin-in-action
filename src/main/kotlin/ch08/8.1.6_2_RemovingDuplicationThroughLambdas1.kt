package ch08.ex1_6_2_RemovingDuplicationThroughLambdas1

/**
 * @author runningpig66
 * @date 2025/12/17 周三
 * @time 22:34
 * §8.1 声明高阶函数 §8.1.6 通过 lambda 去除重复代码
 * P.217 代码清单 8.10 用一个普通方法去除重复代码
 *
 * 现在假设你要计算来自 Mac 用户的相同数据，为了避免重复，可以将平台类型抽象为一个参数。
 */
data class SiteVisit(
    val path: String,
    val duration: Double,
    val os: OS
)

enum class OS {
    WINDOWS, LINUX, MAC, IOS, ANDROID
}

val log: List<SiteVisit> = listOf(
    SiteVisit("/", 34.0, OS.WINDOWS),
    SiteVisit("/", 22.0, OS.MAC),
    SiteVisit("/login", 12.0, OS.WINDOWS),
    SiteVisit("/signup", 8.0, OS.IOS),
    SiteVisit("/", 16.3, OS.ANDROID)
)

// 将重复代码抽取到函数中
// 注意将这个函数作为扩展函数增强了可读性。如果它只在局部的上下文中有用，你甚至可以将这个函数声明为局部扩展函数。
fun List<SiteVisit>.averageDuration(os: OS): Double =
    this.filter { it.os == os }
        .map(SiteVisit::duration)
        .average()

fun main() {
    println(log.averageDuration(OS.WINDOWS))
    println(log.averageDuration(OS.MAC))
}
/* Output:
23.0
22.0
 */
