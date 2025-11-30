package ch05.ex5_1_4_TheWithFunction3

/**
 * @author runningpig66
 * @date 2025/11/30 周日
 * @time 15:06
 * §5.5 带接收者的 lambda: "with" 与 "apply" §5.5.1 "with" 函数
 * P.133 代码清单 方法名称冲突
 *
 * 如果你当作参数传给 with 的对象已经有这样的方法：该方法的名称和你正在使用 with 的类中的方法一样，怎么办？这种情况下，
 * 可以给 this 引用加上显式的标签来表明你要调用的是哪个方法。假设函数 alphabet 是类 OuterClass 的一个方法。
 * 如果你想引用的是定义在外部类的 toString 方法而不是 StringBuilder，可以用下面这种语法：
 * this@OuterClass.toString()
 *
 * public inline fun <T, R> with(receiver: T, block: T.() -> R): R
 */
// 外部类，有自己的 toString()
class OuterClass(private val id: Int) {
    override fun toString() = "OuterClass(id=$id)"

    // 在这个成员函数里使用 with(StringBuilder)
    fun alphabet() = with(StringBuilder()) {
        append("1) 调用接收者 StringBuilder.toString():\n")
        append("   -> ")
        append(this.toString()) // 这里的 toString() 是 StringBuilder 的

        append("\n\n2) 调用外部类 OuterClass.toString():\n")
        append("   -> ")
        append(this@OuterClass.toString()) // 显式指定外部类的 this

        // with 的结果：返回接收者 StringBuilder 的 toString()
        toString()
    }
}

fun main() {
    println(OuterClass(47).alphabet())
}
/* Output:
1) 调用接收者 StringBuilder.toString():
   -> 1) 调用接收者 StringBuilder.toString():
   ->

2) 调用外部类 OuterClass.toString():
   -> OuterClass(id=47)
 */
