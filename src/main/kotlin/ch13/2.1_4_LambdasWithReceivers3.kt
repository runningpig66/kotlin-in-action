package ch13.HtmlTags

/**
 * @author runningpig66
 * @date 3月14日 周六
 * @time 20:53
 */
fun main() {
    val map = mutableMapOf(1 to "one")
    map.apply { this[2] = "two" }
    with(map) { this[3] = "three" }
    println(map)
}
/* Output:
{1=one, 2=two, 3=three}
 */
