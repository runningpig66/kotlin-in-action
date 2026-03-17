package ch13.ex1_InvokeConvention

/**
 * @author runningpig66
 * @date 3月15日 周日
 * @time 23:47
 * Listing 13.16 Defining an invoke method in a class
 * 清单 13.16 在类中定义 invoke 方法
 *
 * 这段代码在 Greeter 中定义了 invoke 方法，它允许你像调用函数一样调用 Greeter 的实例。
 * 在底层，表达式 bavarianGreeter("Dmitry") 会被编译为方法调用 bavarianGreeter.invoke("Dmitry") 。
 * 这里并没有什么神秘之处。它的工作原理就像常规的约定一样，提供了一种用更简洁、更清晰的方式替代冗长表达式的方法。
 */
class Greeter(val greeting: String) {
    operator fun invoke(name: String) { // 在 Greeter 上定义 invoke 方法
        println("$greeting, $name!")
    }
}

fun main() {
    val bavarianGreeter = Greeter("Servus")
    bavarianGreeter("Dmitry") // 将 Greeter 实例作为函数调用
}
/* Output:
Servus, Dmitry!
 */
