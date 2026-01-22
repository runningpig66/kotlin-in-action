package ch11.note

/**
 * @author runningpig66
 * @date 2026/1/19 周一
 * @time 5:07
 * 11.3.4 Contravariance reverses the subtyping relation
 * 11.3.4 逆变：反转子类型关系
 */
sealed class Fruit {
    abstract val weight: Int
}

data class Apple(
    override val weight: Int,
    val color: String
) : Fruit()

data class Orange(
    override val weight: Int,
    val juicy: Boolean
) : Fruit()

fun enumerateCats(f: (Cat) -> Number) {
    val number = f(Cat())
    println(number)
}

fun Animal.getIndex(): Int {
    return 0
}

fun main() {
    val weightComparator = Comparator<Fruit> { a, b ->
        a.weight - b.weight
    }
    val fruits: List<Fruit> = listOf(
        Orange(180, true),
        Apple(100, "green")
    )
    val apples: List<Apple> = listOf(
        Apple(50, "red"),
        Apple(120, "green"),
        Apple(155, "yellow")
    )
    println(fruits.sortedWith(weightComparator))
    println(apples.sortedWith(weightComparator))

    enumerateCats(Animal::getIndex)
}
/* Output:
[Apple(weight=100, color=green), Orange(weight=180, juicy=true)]
[Apple(weight=50, color=red), Apple(weight=120, color=green), Apple(weight=155, color=yellow)]
0
 */
