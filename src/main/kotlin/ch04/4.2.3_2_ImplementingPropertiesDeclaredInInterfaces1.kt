package ch04.ex2_3_2_ImplementingPropertiesDeclaredInInterfaces1

/**
 * @author runningpig6
 * @date 2025/11/22 周六
 * @time 23:30
 * §4.2 声明一个带非默认构造方法或属性的类 §4.2.3 实现在接口中声明的属性
 * P.86 代码清单
 *
 * 除了抽象属性声明外，接口还可以包含具有 getter 和 setter 的属性，
 * 只要它们没有引用一个支持字段（支持字段需要在接口中存储状态，而这是不允许的）。
 */
interface User {
    val email: String

    // nickname 属性没有支持字段：结果值在每次访问时通过计算得到
    val nickname: String
        get() = email.substringBefore('@')
}
