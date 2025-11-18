package ch03.ex3_5_ExtensionProperties

/**
 * @author runningpig66
 * @date 2025/11/19 周三
 * @time 4:47
 * 3.3 给别人的类添加方法：扩展函数和属性：3.3.5 扩展属性
 * P.58 代码清单 3.7 声明一个扩展属性
 * P.58 代码清单 3.8 声明一个可变的扩展属性
 *
 * 可以看到，和扩展函数一样，扩展属性也像接收者的一个普通的成员属性一样。
 * 这里，必须定义 getter 函数，因为没有支持字段，因此没有默认 getter 的实现。
 * 同理，初始化也不可以：因为没有地方储初始值。
 */
val String.lastChar: Char
    get() = this.get(this.length - 1)

// 如果在 StringBuilder 上定义一个相同的属性，可以置为 var, 因为 StringBuilder 的内容是可变的。
var StringBuilder.lastChar: Char
    get() = this.get(this.length - 1) // getter 属性
    set(value) { // setter 属性
        this.setCharAt(this.length - 1, value)
    }

fun main() {
    // 可以像访问使用成员属性一样访问它：
    println("Kotlin".lastChar)
    val sb = StringBuilder("Kotlin?")
    sb.lastChar = '!'
    println(sb)
}
/* Output:
n
Kotlin!
 */
