package ch05.ex2_1_5_FilterMap4

/**
 * @author runningpig66
 * @date 2025/11/28 周五
 * @time 1:49
 * §5.2 集合的函数式 API §5.2.1 基础：filter 和 map
 * P.118 代码清单 filterKeys filterValues mapKeys mapValues
 *
 * filterKeys 和 mapKeys 过滤和变换 map 的键，而另外的 filterValues 和 mapValues 过滤和变换对应的值。
 * public inline fun <K, V> Map<out K, V>.filterKeys(predicate: (K) -> Boolean): Map<K, V>
 * public inline fun <K, V, R> Map<out K, V>.mapKeys(transform: (Map.Entry<K, V>) -> R): Map<R, V>
 * public inline fun <K, V> Map<out K, V>.filterValues(predicate: (V) -> Boolean): Map<K, V>
 * public inline fun <K, V, R> Map<out K, V>.mapValues(transform: (Map.Entry<K, V>) -> R): Map<K, R>
 */
fun main() {
    val numbers = mapOf(0 to "zero", 1 to "one")
    // filterKeys: 根据 key 过滤，保留满足条件的键值对，返回新 Map
    println(numbers.filterKeys { key -> key > 0 })
    // mapKeys: 根据 entry 生成新 key, value 原样保留，返回新 Map
    println(numbers.mapKeys { entry -> "key_${entry.key}" })
    // filterValues: 根据 value 过滤，保留满足条件的键值对，返回新 Map
    println(numbers.filterValues { value -> value.length > 3 })
    // mapValues: 根据 entry 生成新 value, key 原样保留，返回新 Map
    println(numbers.mapValues { it.value.uppercase() })
}
/* Output:
{1=one}
{key_0=zero, key_1=one}
{0=zero}
{0=ZERO, 1=ONE}
 */
