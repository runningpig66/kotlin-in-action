package ch06.ex3_4_2_PersonParser

import ch06.DataParser
import ch06.Person

/**
 * @author runningpig66
 * @date 2025/12/2 周二
 * @time 18:27
 * §6.3 集合与数组 §6.3.4 作为平台类型的集合
 * P.172 代码清单 6.28 DataParser 的 Kotlin 实现
 *
 * 当你重写或者实现签名中有集合类型的 Java 方法时，像平台类型的可空性一样，
 * 你需要决定使用哪一种 Kotlin 类型来表示这个 Java 类型，它来自你要重写或实现的方法。
 *
 * 这种情况下，你要做出多种选择，它们都会反映在产生的 Kotlin 参数类型中：
 * - 集合是否可空？
 * - 集合中的元素是否可空？
 * - 你的方法会不会修改集合？
 */
// 把它和另外一个接口对比。这里接口的实现从文本表单中解析出数据并放到一个对象列表中，再把这些对象附加到输出列表中。
// 当发现解析错误时，就把错误信息添加到另一个单独的列表中，作为错误日志。这种情况下的选择是不同的：
// - List<String> 将是非空的，因为调用者总是需要接收错误消息。
// - 列表中的元素将是可空的，因为不是每个输出列表中的条目都有关联的错误信息。
// - List<String> 将是可变的，因为实现代码需要向其中添加元素。
// 下面是在 Kotlin 中如何实现这个接口。
class PersonParser : DataParser<Person> {
    override fun parseData(
        input: String,
        output: MutableList<Person>,
        errors: MutableList<String?>
    ) {
    }
}
