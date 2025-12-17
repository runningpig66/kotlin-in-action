package ch08.ex1_6_1_RemovingDuplicationThroughLambdas

/**
 * @author runningpig66
 * @date 2025/12/17 周三
 * @time 22:20
 * §8.1 声明高阶函数 §8.1.6 通过 lambda 去除重复代码
 * P.216 代码清单 8.8 定义站点访问数据
 * P.217 代码清单 8.9 使用硬编码的过滤器分析站点访问数据
 *
 * 函数类型和 lambda 表达式一起组成了一个创建可重用代码的好工具。许多以前只能通过复杂笨重的结构来避免的重复代码，
 * 现在可以通过使用简洁的 lambda 表达式被消除。我们来看一个分析网站访问的例子。
 * SiteVisit 类用来保存每次访问的路径、持续时间和用户的操作系统。不同的操作系统使用枚举类型来表示。
 */
data class SiteVisit(
    val path: String,
    val duration: Double,
    val os: OS
)

enum class OS { WINDOWS, LINUX, MAC, IOS, ANDROID }

val log: List<SiteVisit> = listOf(
    SiteVisit("/", 34.0, OS.WINDOWS),
    SiteVisit("/", 22.0, OS.MAC),
    SiteVisit("/login", 12.0, OS.WINDOWS),
    SiteVisit("/signup", 8.0, OS.IOS),
    SiteVisit("/", 16.3, OS.ANDROID)
)

// 想象一下如果你需要显示来自 Windows 机器的平均访问时间，可以用 average 函数来完成这个任务。
val averageWindowsDuration: Double = log
    .filter { it.os == OS.WINDOWS }
    .map(SiteVisit::duration)
    .average()

fun main() {
    println(averageWindowsDuration)
}
/* Output:
23.0
 */
