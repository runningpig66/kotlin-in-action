package ch08.ex1_5_1_ReturningFunctionsFromFunctions

/**
 * @author runningpig66
 * @date 2025/12/16 周二
 * @time 22:12
 * §8.1 声明高阶函数 §8.1.5 返回函数的函数
 * P.214 代码清单 8.6 定义一个返回函数的函数
 *
 * 从函数中返回另一个函数并没有将函数作为参数传递那么常用，但它仍然非常有用。
 * 想象一下程序中的一段逻辑可能会因为程序的状态或者其他条件而产生变化————比如说，运输费用的计算依赖于选择的运输方式。
 * 可以定义一个函数用来选择恰当的逻辑变体并将它作为另一个函数返回。以下是具体的代码。
 *
 * 声明一个返回另一个函数的函数，需要指定一个函数类型作为返回类型。在代码清单 8.6 中，
 * getShippingCostCalculator 返回了一个函数，这个函数以 Order 作为参数并返回一个 Double 类型的值。要返回一个函数，
 * 需要写一个 return 表达式，跟上一个 lambda、一个成员引用，或者其他的函数类型的表达式，比如一个（函数类型的）局部变量。
 */
enum class Delivery { STANDARD, EXPEDITED }
class Order(val itemCount: Int)

// 声明一个返回函数的函数
fun getShippingCostCalculator(delivery: Delivery): (Order) -> Double {
    if (delivery == Delivery.EXPEDITED) {
        return { order -> 6 + 2.1 * order.itemCount } // 返回 lambda
    }
    return { order -> 1.2 * order.itemCount } // 返回 lambda
}

fun main() {
    // 将返回的函数保存在变量中
    val calculator: (Order) -> Double = getShippingCostCalculator(Delivery.EXPEDITED)
    // 调用返回的函数
    println("Shipping costs ${calculator(Order(3))}")
}
/* Output:
Shipping costs 12.3
 */
