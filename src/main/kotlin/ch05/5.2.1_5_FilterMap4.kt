package ch05.ex2_1_5_FilterMap4

// 代码清单 P.118
fun main() {
    val numbers = mapOf(0 to "zero", 1 to "one")
    // filterKeys: 保留符合条件的 key，value 不变
    println(numbers.filterKeys { key -> key > 0 })
    // mapKeys: 对每个 key 进行转换，生成新的 Map，value 不变
    println(numbers.mapKeys { entry -> "key_${entry.key}" })
    // filterValues: 保留符合条件的 value，key 不变
    println(numbers.filterValues { value -> value.length > 3 })
    // mapValues: 对每个 value 进行转换，生成新的 Map，key 不变
    println(numbers.mapValues { it.value.uppercase() })
}
/** Output:
 * {1=one}
 * {key_0=zero, key_1=one}
 * {0=zero}
 * {0=ZERO, 1=ONE}
 */




