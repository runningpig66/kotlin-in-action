package ch04.ex2_2_Constructor

/**
 * @author runningpig66
 * @date 2025/11/22 周六
 * @time 22:21
 * §4.2 声明一个带非默认构造方法或属性的类 §4.2.2 构造方法：用不同的方式来初始化父类
 * P.84 代码清单
 */
interface Context
interface AttributeSet
open class View {
    // 从构造方法
    constructor(ctx: Context) {
        // some code
    }

    // 从构造方法
    constructor(ctx: Context, attr: AttributeSet?) {
        // some code
    }
}

class MyButton : View {
    // 调用父类构造方法
//     constructor(ctx: Context) : super(ctx) {
//         // ...
//     }

    // 委托给这个类的另一个构造方法
    constructor(ctx: Context) : this(ctx, null) {
        // ...
    }

    // 调用父类构造方法
    constructor(ctx: Context, attr: AttributeSet?) : super(ctx, attr) {
        // ...
    }
}
