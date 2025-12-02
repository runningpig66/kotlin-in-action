package ch06.ex2_5_UnitType

/**
 * @author runningpig66
 * @date 2025/12/2 周二
 * @time 13:24
 * §6.2 基本数据类型和其他基本类型 §6.2.5 Unit 类型：Kotlin 的 "void"
 * P.163 代码清单
 *
 * Kotlin 中的 Unit 类型完成了 Java 中的 void 一样的功能。当函数没什么有意思的结果要返回时，它可以用作函数的返回类型。
 *
 * Unit 是一个完备的类型，可以作为类型参数，而 void 却不行。只存在一个值是 Unit 类型，这个值也叫作 Unit,
 * 并且（在函数中）会被隐式地返回。当你在重写返回泛型参数的函数时这非常有用，只需要让方法返回 Unit 类型的值。
 */
fun f(): Unit {}
fun f1() {} // 显式的 Unit 声明被省略了

interface Processor<T> {
    fun process(): T
}

class NoResultProcessor : Processor<Unit> {
    // 返回 Unit, 但可以省略类型说明
    override fun process() {
        // 这里不需要显式的 return, 编译器会隐式地加上 return Unit
    }
}
