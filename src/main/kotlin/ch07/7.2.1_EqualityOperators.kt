package ch07.ex2_1_EqualityOperators

/**
 * @author runningpig66
 * @date 2025/12/4 周四
 * @time 21:50
 * §7.2 重载比较运算符 §7.2.1 等号运算符："equals"
 * P.186 代码清单 7.7 实现 equals 函数
 *
 * 如果在 Kotlin 中使用 == 运算符，它将被转换成 equals 方法的调用。equals 方法可以按约定调用。
 * 使用 != 运算符也会被转换成 equals 函数的调用，编译器会自动对返回值取反。
 *
 * 注意，和所有其他运算符不同的是，== 和 != 可以用于可空运算数，因为这些运算符事实上会检查运算数是否为 null.
 * 比较 a==b 会检查 a 是否为非空，如果不是，就调用 a.equals(b); 否则，只有两个参数都是空引用，结果才是 true.
 *
 * equals 函数之所以被标记为 override, 那是因为与其他约定不同的是，这个方法的实现是在 Any 类中定义的
 * （Kotlin 中的所有对象都支持等式比较）。这也解释了为什么你不需要将它标记为 operator:
 * Any 中的基本方法就已经标记了，而且函数的 operator 修饰符也适用于所有实现或重写它的方法。
 * 还要注意，equals 不能实现为扩展函数，因为继承自 Any 类的实现始终优先于扩展函数。
 *
 * any.public actual open operator fun equals(other: Any?): Boolean
 */
// 对于 Point 类，因为已经被标记为数据类，equals 的实现将会由编译器自动生成。但如果要手动实现，那么代码可以是这样的。
class Point(val x: Int, val y: Int) : Any() {
    // 重写在 Any 中定义的方法，operator 修饰符已经被用在了基类的接口中，因此在重写该接口时无需再重复。
    override fun equals(other: Any?): Boolean {
        // 优化：检查参数是否与 this 是同一个对象
        if (other === this) return true
        // 检查参数类型
        if (other !is Point) return false
        // 智能转换为 Point 来访问 x, y 属性
        return other.x == x && other.y == y
    }
}

fun main() {
    println(Point(10, 20) == Point(10, 20))
    println(Point(10, 20) != Point(5, 5))
    println(null == Point(1, 2))
}
/* Output:
true
true
false
 */
