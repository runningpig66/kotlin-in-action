package ch05.ex4_2_SAMConstructors

/**
 * @author runningpig66
 * @date 2025/11/29 周六
 * @time 17:45
 * §5.4 使用 Java 函数式接口 §5.4.2 SAM 构造方法：显式地把 lambda 转换成函数式接口
 * P.130 代码清单 5.14 使用 SAM 构造方法来返回值
 *
 * SAM 构造方法是编译器生成的函数，让你执行从 lambda 到函数式接口实例的显式转换。可以在编译器不会自动应用转换的上下文中使用它。
 * 例如，如果有一个方法返回的是一个函数式接口的实例，不能直接返回一个 lambda, 要用 SAM 构造方法把它包装起来。
 *
 * SAM 构造方法的名称和底层函数式接口的名称一样，SAM 构造方法只接收一个参数————
 * 一个被用作函数式接口单抽象方法体的 lambda ————并返回实现了这个接口的类的一个实例。
 */
fun createAllDoneRunnable(): Runnable {
    return Runnable { println("All done!") }
}

fun main() {
    createAllDoneRunnable().run()
}
/* Output:
All done!
 */
