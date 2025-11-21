package strings

/**
 * @author runningpig66
 * @date 2025/11/19 周三
 * @time 3:33
 * §3.3 给别人的类添加方法：扩展函数和属性
 * P.53 代码清单
 *
 * 在扩展函数中，可以直接访问被扩展的类的其他方法和属性，就好像是在这个类自已的方法中访问它们一样。
 * 注意，扩展函数并不允许你打破它的封装性。和在类内部定义的方法不同的是，扩展函数不能访问私有的或者是受保护的成员。
 */
//fun String.lastChar(): Char = this.get(this.length - 1)
fun String.lastChar(): Char = get(length - 1) // 接收者对象成员可以不用 this 来访问
fun main() {
    println("Kotlin".lastChar())
}
/* Output:
n
 */
