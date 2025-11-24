package ch04.ex4_1_1_ObjectDeclarations

import java.io.File

/**
 * @author runningpig66
 * @date 2025/11/23 周日
 * @time 23:38
 * §4.4 "object" 关键字：将声明一个类与创建一个实例结合起来 §4.4.1 对象声明：创建单例易如反掌
 * P.96 代码清单 4.23 使用对象来实现 Comparator
 *
 * 对象声明将类声明与该类的单一实例声明结合到了一起。与类一样，一个对象声明也可以包含属性、方法、初始化语句块等的声明。
 * 唯一不允许的就是构造方法（包括主构造方法和从构造方法）。与普通类的实例不同，对象声明在定义的时候就立即创建了，
 * 不需要在代码的其他地方调用构造方法。因此，为对象声明定义一个构造方法是没有意义的。对象声明同样可以继承自类和接口。
 */
object CaseInsensitiveFileComparator : Comparator<File> {
    override fun compare(file1: File, file2: File): Int {
        return file1.path.compareTo(file2.path, ignoreCase = true)
    }
}

fun main() {
    println(CaseInsensitiveFileComparator.compare(File("/User"), File("/user")))
    val files = listOf(File("/Z"), File("/a"))
    // 可以在任何可以使用普通对象的地方使用单例对象
    println(files.sortedWith(CaseInsensitiveFileComparator))
}
/* Output:
0
[\a, \Z]
 */
