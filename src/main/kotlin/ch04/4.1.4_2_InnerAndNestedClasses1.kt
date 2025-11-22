package ch04.ex1_4_2_InnerAndNestedClasses1

/**
 * @author runningpig66
 * @date 2025/11/22 周六
 * @time 16:38
 * §4.1 定义类继承结构 §4.1.4 内部类和嵌套类：默认是嵌套类
 * P.78 代码清单
 *
 * 在 Kotlin 中引用外部类实例的语法也与 Java 不同。需要使用 this@Outer 从 Inner 类去访问 Outer 类：
 */
class Outer {
    inner class Inner {
        fun getOuterReference(): Outer = this@Outer
    }
}
