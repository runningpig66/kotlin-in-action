package ch05.ex1_1_Lambdas

/**
 * @author runningpig66
 * @date 2025/11/25 周二
 * @time 11:13
 * §5.1 Lambda 表达式和成员引用 §5.1.1 Lambda 简介：作为函数参数的代码块
 * P.106 代码清单 5.2 用 lambda 实现监听器
 *
 * notes: Lambdas.md
 * Kotlin Lambda、SAM 转换与 fun interface 速记
 */
interface View

// Kotlin 的函数式接口（支持 SAM）
fun interface OnClickListener {
    fun onClick(view: View)
}

class Button() : View {
    private var listener: OnClickListener? = null

    fun setOnClickListener(listener: OnClickListener?) {
        this.listener = listener
    }

    fun onClick() {
        listener?.onClick(this)
    }
}

fun main() {
    // 1. 匿名对象 —— 传统写法，等价于 Java 的匿名内部类
    val button1 = Button()
    button1.setOnClickListener(object : OnClickListener {
        override fun onClick(view: View) {
            println("button1 clicked")
        }
    })
    button1.onClick()
    // 为了演示匿名对象创建后的类型：
    val listener1: OnClickListener = object : OnClickListener {
        override fun onClick(view: View) {
            println("button1_1 clicked")
        }
    }
    button1.setOnClickListener(listener1)
    button1.onClick()

    // 2. 直接在参数位置用 lambda —— SAM 转换（编译器自动包一层）
    val button2 = Button()
    button2.setOnClickListener { view: View ->
        println("button2 clicked")
    }
    button2.onClick()

    // *_3. 保存 lambda 再传入 [参考注释：5.1.3_4_SyntaxForLambdaExpressions3.kt 的 val getAge 部分]
    // ? 指明 val 变量的类型（函数类型）是没有问题的。见 notes.
    val _listener3: (View) -> Unit = { view: View ->
        println("button3 clicked")
    }
    // *? 可以指定为接口类型吗？编译期看起来可以通过 as 手动把函数类型转换到接口类型，但在运行时却出现转换异常：
    // Lambda/0x0000027f0e003218 cannot be cast to class OnClickListener
    // (Lambda/0x0000027f0e003218 and OnClickListener are in unnamed module of loader 'app')
    // val listener3: OnClickListener = { view: View -> println("button3 clicked") } as OnClickListener
    // *From notes: lambda → Function1<View, Unit>, OnClickListener 是单独的接口类型,
    // as 只做检查 + 强转，它不会触发任何“把 lambda 适配成接口”的魔法，不会帮你生成实现类，所以必崩。

    // *3. 函数类型变量 —— 纯函数类型，跟 OnClickListener 不是一个类型
    val fn: (View) -> Unit = { view: View -> println("button3 clicked") }
    // 想变成 OnClickListener，必须用 SAM 构造器：
    val listener3: OnClickListener = OnClickListener(fn)
    val button3 = Button()
    button3.setOnClickListener(listener3)
    button3.onClick()

    // *? OnClickListener { ... } 是什么语法？
    // *3.1 OnClickListener { ... }` —— fun interface 的构造器语法
    val listener3_1: OnClickListener = OnClickListener { view: View -> println("button3_1 clicked") }
    val button3_1 = Button()
    button3_1.setOnClickListener(listener3_1)
    button3_1.onClick()
    // 也可以直接调用
    listener3_1.onClick(button3_1)
}
/* Output:
button1 clicked
button1_1 clicked
button2 clicked
button3 clicked
button3_1 clicked
button3_1 clicked
 */
