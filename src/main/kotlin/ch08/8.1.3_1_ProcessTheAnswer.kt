@file:JvmName("ProcessTheAnswer")

package ch08.ProcessTheAnswer

/**
 * @author runningpig66
 * @date 2025/12/13 周六
 * @time 21:56
 * §8.1 声明高阶函数 §8.1.3 在 Java 中使用函数类
 * P.211 代码清单
 *
 * 其背后的原理是，函数类型被声明为普通的接口：一个函数类型的变量是 FunctionN 接口的一个实现。
 * Kotlin 标准库定义了一系列的接口，这些接口对应于不同参数数量的函数：
 * Function0<R>（没有参数的函数）、Function1<P1,R>（一个参数的函数），等等。
 * 每个接口定义了一个 invoke 方法，调用这个方法就会执行函数。
 * 一个函数类型的变量就是实现了对应的 FunctionN 接口的实现类的实例，实现类的 invoke 方法包含了 lambda 函数体。
 */
fun processTheAnswer(f: (Int) -> Int) {
    println(f(42))
}
