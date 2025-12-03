package ch07.ex1_2_3_CompoundAssignmentOperators3

/**
 * @author runningpig66
 * @date 2025/12/3 周三
 * @time 15:24
 * §7.1 重载算术运算符 §7.1.2 重载复合赋值运算符
 * P.184 代码清单
 *
 * notes: CompoundAssignmentOperators.md & 底部注释笔记
 * 书中 P.184 “另一个办法是，用 val 替换 var，这样 plusAssign 运算就不再适用。”这句话描述不准确。
 */
data class Point(var x: Int, var y: Int)

// plus 函数也可以不返回新的 Point 而就地修改
operator fun Point.plus(that: Point): Point {
    return Point(x + that.x, y + that.y)
}

// plusAssign 函数也可以不就地修改什么都不做，但只能返回 Unit
operator fun Point.plusAssign(that: Point) {
    x += that.x;
    y += that.y
}

fun main() {
//     var p1 = Point(10, 20)           // [1]
    val p1 = Point(10, 20)       // [2]
    val p2 = Point(30, 40)
    val p3 = p1 + p2
    p1 += p2                            // [3]
}
/* 注意：
在 [1] 处打开、[2] 处注释的状态，[3] 处代码报错，编译器无法判断 += 运算符应该调用 plus 还是 plusAssign.
+= 可以映射到 plus 函数，此时 p1 += p2 相当于 p1 = p1.plus(p2),
p1 是 var 可变引用，可以被重新赋值指向新的对象，所以 += 可以映射到 plus 函数（通过临时注释 plusAssign 函数可以印证这一点）。
+= 也可以映射到 plusAssign 函数，此时的 += 只是相当于 plusAssign 函数的语法糖，
p1 += p2 相当于 p1.plusAssign(p2). 故两个函数都可以被映射，导致编译器无法区分而报错。

如果注释掉 [1] 打开 [2] , 编译器不再报错。原因是 p1 变为了不可变引用。
当 p1 作为不可变引用时，对于 plus 函数，p1 += p2 相当于 p1 = p1.plus(p2) 中的 p1 无法再赋值，
所以此时的 plus 函数只能对运算符 + 生效，无法对潜在的 += 生效。
而 += 对于 plusAssign 函数依然有效，同上，+= 只是相当于 plusAssign 函数的语法糖，
p1 是可变引用，还是不可变引用，都对 p1 调用 plusAssign 函数没有影响。
而在 plusAssign 函数的内部会对 p1 的成员进行就地修改，而不是返回新的修改后的对象。
即：p1 作为不可变引用调用 += ，只能映射到 plusAssign 函数。

那么此时注意书中的描述：“另一个办法是，用 val 替换 var，这样 plusAssign 运算就不再适用。”
而我们上方所做的，是用 val 替换 var，plusAssign 运算照样适用，只要对象内容可以就地修改。

“用 val 替换 var，plusAssign 运算就不再适用”这句话并不准确，书中的描述可能是在讨论不可变对象的情况：
即如果我们使用不可变对象（类的属性是val），那么 plusAssign 函数无法实际修改属性，因此就不适用了。
在这种情况下，我们只能使用 plus 函数，并且 p1 必须是 var 才能进行 += 操作（因为需要重新赋值）。
书中的描述应修正为“用 val 替换 var 会使 plus 不适用于 +=，但 plusAssign 仍然适用”

我们通常的做法是：
- 对于不可变类，只定义 plus 等返回新对象的运算符函数。
- 对于可变类，可以定义 plusAssign 等就地修改的运算符函数，但不要同时定义 plus（除非有特别需要，并且注意避免冲突）。
+= 会优先调用 plusAssign，如果没有，再尝试用 plus（要求左值是 var）。
*/
