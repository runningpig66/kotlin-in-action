package ch04.ex1_2_2_OpenFinalAbstractModifiers1

/**
 * @author runningpig66
 * @date 2025/11/22 周六
 * @time 4:09
 * §4.1 定义类继承结构 §4.1.2 open、final 和 abstract 修饰符：默认为 final
 * P.73 代码清单 4.7 禁止重写
 *
 * 注意，如果你重写了一个基类或者接口的成员，重写了的成员同样默认是 open 的。
 * 如果你想改变这一行为，阻止你的类的子类重写你的实现，可以显式地将重写的成员标注为 final.
 */
interface Clickable {
    fun click()
    fun showOff() = println("I'm clickable!")
}

open class RichButton : Clickable {
    // 在这里 "final" 并没有被删减是因为没有 "final" 的 "override" 意味着是 open 的
    final override fun click() {}
}
