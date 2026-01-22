package ch11.FieldValidator

import kotlin.reflect.KClass

/**
 * @author runningpig66
 * @date 2026/1/19 周一
 * @time 11:21
 * Listing 11.18 Interfaces for input validation
 * 代码清单 11.18 用于输入验证的接口
 */
// Interface declared as contravariant on T
interface FieldValidator<in T> {
    // T 仅用于 in 位置（该方法消费 T 类型的值）。
    fun validate(input: T): Boolean
}

object DefaultStringValidator : FieldValidator<String> {
    override fun validate(input: String) = input.isNotEmpty()
}

object DefaultIntValidator : FieldValidator<Int> {
    override fun validate(input: Int) = input > 0
}

@Suppress("UNCHECKED_CAST")
fun main() {
    val validators = mutableMapOf<KClass<*>, FieldValidator<*>>()
    validators[String::class] = DefaultStringValidator
    validators[Int::class] = DefaultIntValidator

    // The value stored in the map has the type FieldValidator<*>.
    // Error: Out-projected type 'FieldValidator<*>' prohibits the use of 'fun validate(input: T): Boolean'
    //- validators[String::class]!!.validate("")

    // Listing 11.19 Retrieving a validator using an explicit cast
    // 清单 11.19 使用显式转换获取验证器
    // Warning: Unchecked cast of 'FieldValidator<*>?' to 'FieldValidator<String>'.
    val stringValidator = validators[String::class] as FieldValidator<String>
    println(stringValidator.validate(""))

    // Listing 11.20 Incorrectly retrieving a validator
    // 清单 11.20 错误地获取验证器
    // You get an incorrect validator (possibly by mistake), but this code compiles.
    val stringValidator2 = validators[Int::class] as FieldValidator<String> // It‘s only a warning.
    // The real error is hidden until you use the validator.
    //- stringValidator2.validate("")
}
/* Output:
false
 */
