package ch08.ex1_6_3_RemovingDuplicationThroughLambdas2

/**
 * @author runningpig66
 * @date 2025/12/17 周三
 * @time 22:55
 * §8.1 声明高阶函数 §8.1.6 通过 lambda 去除重复代码
 * P.217 代码清单 8.11 用一个复杂的硬编码函数分析站点访问数据
 *
 * 但这还远远不够。想象一下，如果你对来自移动平台（目前你识别出来的只有两种：iOS 和 Android）的访问的平均时间非常有兴趣。
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
val averageMobileDuration = log
    .filter { it.os in setOf(OS.IOS, OS.ANDROID) }
    .map { it.duration }
    .average()

fun main() {
    println(averageMobileDuration)
}
/* Output:
12.15
 */
