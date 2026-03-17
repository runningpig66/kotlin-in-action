package ch13.notes

/**
 * @author runningpig66
 * @date 3月16日 周一
 * @time 15:18
 * 13.4.1 Chaining infix calls: The should function in test frameworks
 * 13.4.1 链式中缀调用：测试框架中的 should 函数
 * Let’s look at an example that uses the DSL of Kotest (https://github.com/kotest/kotest)
 */
// 泛型匹配器契约接口：其实现类需在 [test] 方法内封装具体的校验策略，用于验证传入的目标数据 [value] 是否满足预设的断言规则。
interface Matcher<T> {
    fun test(value: T)
}

infix fun <T> T.should(matcher: Matcher<T>) = matcher.test(this)

fun main() {
    /* 阶段 1：基于匿名内部类的静态规则实现 (硬编码模式)
     * 此处通过对象表达式 (Object Expression) 实例化单一契约接口的最基础方式。
     * 该实现将匹配规则（前缀 "k"）作为字面量直接硬编码在方法体内部。特征：
     * 1. 作用域封闭：内部逻辑仅依赖于入参 [value] 与局部字面量，未发生任何对外部作用域的变量捕获（无闭包形成）。
     * 2. 局限性：由于规则被静态绑定在类的内部实现中，该对象成为一个高度耦合的“专用匹配器”，
     * 无法通过外部传参来复用逻辑，缺乏作为框架基础组件所需的通用性与扩展性。
     */
    val matcher1 = object : Matcher<String> {
        override fun test(value: String) {
            // 缺陷：测试规则 "k" 被硬编码。编译器在生成此内部类时，不需要也不包含任何外部状态的引用。
            println(value.startsWith("k"))
        }
    }
    // 调用端：显式调用重写的成员方法进行测试
    matcher1.test("kotlin")

    /* 阶段 2：引入外部参数与闭包的初步尝试 (局部作用域限制)
     * 此处通过外部方法接收动态参数 [prefix]，希望解决阶段一中匹配规则硬编码的问题。
     * 内部的对象表达式不再依赖静态字面量，而是动态获取匹配条件。特征与缺陷：
     * 1. 变量捕获与闭包 (Closure)：匿名内部类的重写方法中，跨作用域引用了外部方法的局部入参 [prefix]。
     * 在底层字节码层面，编译器会隐式生成带有构造参数的真实类，并将 [prefix] 保存为其私有成员变量，由此正式形成了闭包。
     * 2. 职责耦合与倒置硬编码：虽然匹配规则（前缀）获得了灵活性，但匹配器实例 [matcher2] 的生命周期被严格限制在当前方法栈内。
     * 为了在方法内部完成验证，被迫将待测试的目标数据（"kotlin"）写死，导致了新的硬编码问题。
     * 3. 设计局限：此处的缺陷在于将匹配器的“构建”与“执行”强行耦合在了同一个方法的上下文中。
     * 作为一个合格的基础组件，必须将构建好的实例传递（return）给外部环境，交由调用者决定测试的目标对象与执行时机。
     */
    fun prefixMatch(prefix: String) {
        // 实例化一个持有外部变量 prefix 状态的闭包对象
        val matcher2 = object : Matcher<String> {
            override fun test(value: String) {
                // 内部类在此处访问了外部方法参数 [prefix]，发生了变量捕获 (Variable Capture)
                println(value.startsWith(prefix))
            }
        }
        // 缺陷：由于无法将 matcher2 暴露给外部，只能在内部立即执行，导致目标数据 "kotlin" 被硬编码
        matcher2.test("kotlin")
    }
    // 调用端：传入动态的前缀规则，但失去了对目标数据进行灵活测试的能力
    prefixMatch("k")

    /* 阶段 3：基于工厂方法的闭包返回与执行解耦 (组件化实现)
     * 此阶段通过工厂方法 (Factory Method) 模式解决了前两阶段的局限性。
     * [startWith] 函数接收动态的前缀规则，构建并返回一个捕获了该规则的匹配器闭包实例。特征与优势：
     * 1. 彻底解耦：匹配器的“实例化构建”（规则定义）与“测试执行”（数据验证）被完全分离。
     * 工厂方法仅负责生产状态完备的组件，不再越权干涉执行逻辑。
     * 2. 状态保持与传递：返回的 [matcher] 对象是一个携带有外部 [prefix] 状态的独立闭包。
     * 该对象脱离了原有的方法作用域，可以在外部环境针对任意目标数据被多次重复调用。
     * 3. 奠定 DSL 基础：这种高度灵活、职责单一的组件设计，为上层封装更为抽象的语法糖（如中缀调用）提供了底层支撑。
     */
    fun startWith(prefix: String): Matcher<String> {
        // 实例化闭包对象，内部访问并捕获入参 [prefix]
        val matcher = object : Matcher<String> {
            override fun test(value: String) {
                println(value.startsWith(prefix))
            }
        }
        // 关键改进：将闭包实例返回给调用方，交出执行控制权
        return matcher
    }
    // 调用端：1. 通过工厂方法获取携带特定规则 ("k") 的匹配器闭包实例
    val matcher3 = startWith("k")
    // 调用端：2. 由外部环境决定执行时机，并传入任意待测试的目标数据
    matcher3.test("kotlin")
    // 封装应用：结合前期定义的 Matcher 接口与 infix 扩展函数 (should)，
    // 将底层的对象创建与方法调用，映射为接近自然语言的声明式断言规范。
    "kotlin" should startWith("k")
}
/* Output:
true
true
true
true
 */
