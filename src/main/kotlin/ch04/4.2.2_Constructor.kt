package ch04.ex2_2_Constructor

import ch04.AttributeSet
import ch04.Context
import ch04.View

// 代码清单 P.84
//interface Context
//interface AttributeSet
//open class View {
//    constructor(ctx: Context) {
//        // some code
//    }
//
//    constructor(ctx: Context, attr: AttributeSet?) {
//        // some code
//    }
//}

class MyButton : View {
    // 调用父类的构造方法
//    constructor(ctx: Context) : super(ctx) {
//        // ...
//    }

    // 委托给这个类的另一个构造方法
    constructor(ctx: Context) : this(ctx, null)

    constructor(ctx: Context, attr: AttributeSet?) : super(ctx, attr) {
        // ...
    }
}