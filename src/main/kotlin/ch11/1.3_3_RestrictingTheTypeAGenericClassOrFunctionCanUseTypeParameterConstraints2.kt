package ch11.ex3_3_RestrictingTheTypeAGenericClassOrFunctionCanUseTypeParameterConstraints2

import ch11.JBox

/**
 * @author runningpig66
 * @date 2026/1/14 周三
 * @time 20:48
 * 11.1 Creating types with type arguments: Generic type parameters
 * 11.1 使用类型实参创建类型：泛型类型参数
 * 11.1.3 Restricting the type a generic class or function can use: Type parameter constraints
 * 11.1.3 限制泛型类或函数可以使用的类型：类型参数约束
 * 11.1.4 Excluding nullable type arguments by explicitly marking type parameters as non-null
 * 11.1.4 通过显式将类型参数标记为非空来排除可空类型实参
 * Listing 11.4 Specifying multiple constraints for a type parameter
 */
// List of type parameter constraints
fun <T> ensureTrailingPeriod(seq: T) where T : CharSequence, T : Appendable {
    // Calls an extension function defined for the CharSequence interface
    if (!seq.endsWith('.')) {
        // Calls the method from the Appendable interface
        seq.append('.')
    }
}

// 泛型 T 若未指定上界，默认归约为 Any?，即该变量本质上是可空的。
// 此时直接调用 value.hashCode() 无需安全调用符 (?.) 也能编译通过。
// 因为这里调用的是 Kotlin 为 Any? 定义的“扩展函数”，而非成员方法。
// 该扩展函数内部已处理了 null 情况 (null 时的 hashCode 为 0)，因此允许在 null 接收者上直接调用。
// public inline fun Any?.hashCode(): Int = this?.hashCode() ?: 0
class Processor<T> {
    fun process(value: T) {
        // value.hashCode(): 调用扩展函数。如果 value 是 null，返回 Int 类型的 0。
        val hashCode1: Int = value.hashCode()
        // value?.hashCode(): 使用安全调用。如果 value 是 null，整个表达式返回 null（类型变成 Int?）。
        val hashCode2: Int? = value?.hashCode() // value is nullable, so you have to use a safe call.
    }
}

// Specifying a non-null upper bound
class Processor2<T : Any> {
    fun process(value: T) {
        // The value of type T is now non-null.
        value.hashCode()
    }
}

// Because the generic type T was already constrained to be non-nullable here ...
class KBox<T : Any> : JBox<T> {
    override fun put(t: T) {
    }

    // ... you can no longer relax this constraint for a function that expects a nullable parameter.
    override fun putIfNotNull(t: T) {
        /*
        Problem!
        书中所说的 "Problem" 不是指你的 class KBox 写不出来，而是指你写出来的这个类，丧失了 Java 接口原本设计的意图。
        Java 接口的契约是：putIfNotNull: 如果传入 null 就不做任何事，如果传入非空值就放入盒子。这意味着这个方法应该允许传入 null。
        但是，由于你的 KBox 定义了 <T : Any>（T 必须非空），你在 Kotlin 中使用这个类时，就再也无法向 putIfNotNull 传入 null 了。
        */
    }
}

// 如果你确定你的业务场景中，这个盒子永远不需要支持 String?、Int? 这种类型，那么 KBox2 是更干净、更简单的选择。
// 但如果你是在编写一个通用库，或者通过实现 Java 接口来提供标准行为，那么你不能擅自添加 <T : Any> 的约束，否则你就无意中缩小了该接口的适用范围。
// 这时候，就必须用 KBox3 的方式（T & Any）来在“保持泛型宽泛”的同时“满足特定方法的非空契约”。
class KBox2<T : Any> : JBox<T> {
    override fun put(t: T) {
    }

    override fun putIfNotNull(t: T?) {
    }
}

// 虽然 put 方法强迫非空（因为 T & Any），但这个类本身是允许 T 为 String? 的。这很关键，也许这个类还有 get(): T 方法，用户希望能取回 null。
class KBox3<T> : JBox<T> {
    // T & Any（强制非空）
    // & Any 这个语法的唯一作用是：把一个可能是 null 的泛型，强制变成绝对不为 null 的类型。& Any 只能用于那些“原本可能是 null”的泛型参数上。
    override fun put(t: T & Any) {
        // TEMP: Error: Intersection types are supported only for definitely non-nullable types: left part must be a type parameter with nullable bounds.
    }

    // T?（强制可空）
    override fun putIfNotNull(t: T?) {
    }
}

fun main() {
    val helloWorld = StringBuilder("Hello World")
    ensureTrailingPeriod(helloWorld)
    println(helloWorld)

    // String?, which is a nullable type, is substituted for T.
    val nullableStringProcessor = Processor<String?>()
    // This code compiles fine, having null as the value argument.
    nullableStringProcessor.process(null)

    // Error: Type argument is not within its bounds: must be subtype of 'Any'.
    //- val nullableStringProcessor = Processor2<String?>()
}
/* Output:
Hello World.
 */
