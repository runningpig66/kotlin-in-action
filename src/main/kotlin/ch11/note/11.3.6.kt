package ch11.note

import kotlin.random.Random

/**
 * @author runningpig66
 * @date 2026/1/19 周一
 * @time 11:01
 * 11.3.6 Star projection: Using the * character to indicate a lack of information about a generic argument
 * 11.3.6 星号投影：使用 * 字符表示缺少泛型实参信息
 */
fun main() {
    val list: MutableList<Any?> = mutableListOf('a', 1, "qwe")
    val chars: MutableList<Char> = mutableListOf('a', 'b', 'c')
    // MutableList<*> isn’t the same as MutableList<Any?>.
    // MutableList<*> 等同于 Kotlin 中的 MutableList<out Any?>, Java 中的 MutableList<?>, MutableList<? extends Object>
    val unknownElements: MutableList<*> =
        if (Random.nextBoolean()) list else chars
    // It’s safe to get elements: first() returns an element of the Any? type.
    println(unknownElements.first())
    // The compiler forbids you to call this method.
    // Error: Out-projected type 'MutableList<*>' prohibits the use of 'fun add(element: E): Boolean'
    //- unknownElements.add(42)
}
/* Output:
a
 */
