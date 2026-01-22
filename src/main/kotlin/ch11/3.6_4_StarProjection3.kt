package ch11.ex6_4_StarProjection3

import kotlin.reflect.KClass

/**
 * @author runningpig66
 * @date 2026/1/22 周四
 * @time 20:03
 * Listing 11.21 Encapsulating access to the validator collection
 * 代码清单 11.21 封装对验证器集合的访问
 */
interface FieldValidator<in T> {
    fun validate(input: T): Boolean
}

object DefaultStringValidator : FieldValidator<String> {
    override fun validate(input: String) = input.isNotEmpty()
}

object DefaultIntValidator : FieldValidator<Int> {
    override fun validate(input: Int) = input > 0
}

object Validators {
    // [1] Uses the same map as before, but now, you can’t access it outside
    private val validators =
        mutableMapOf<KClass<*>, FieldValidator<*>>()

    fun <T : Any> registerValidator(kClass: KClass<T>, fieldValidator: FieldValidator<T>) {
        // [2] Puts only the correct key-value pairs into the map, when a validator corresponds to a class
        validators[kClass] = fieldValidator
    }

    // [3] Suppresses the warning about the unchecked cast to FieldValidator
    @Suppress("UNCHECKED_CAST")
    operator fun <T : Any> get(kClass: KClass<T>): FieldValidator<T> =
        // Warning: Unchecked cast of 'FieldValidator<*>?' to 'FieldValidator<T (of fun <T : Any> get)>'.
        validators[kClass] as? FieldValidator<T>
            ?: throw IllegalArgumentException("No validator for ${kClass.simpleName}")
}

fun main() {
    val validators = mutableMapOf<KClass<*>, FieldValidator<*>>()
    validators[String::class] = DefaultStringValidator
    validators[Int::class] = DefaultIntValidator

    Validators.registerValidator(String::class, DefaultStringValidator)
    Validators.registerValidator(Int::class, DefaultIntValidator)
    println(Validators[String::class].validate("Kotlin"))
    println(Validators[Int::class].validate(42))

    // Error: Argument type mismatch: actual type is 'Int', but 'String' was expected.
    //- println(Validators.get(String::class).validate(42))
}
/* Output:
true
true
 */
