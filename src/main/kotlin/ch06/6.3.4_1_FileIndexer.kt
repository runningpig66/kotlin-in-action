package ch06.ex3_4_1_FileIndexer

import ch06.FileContentProcessor
import java.io.File

/**
 * @author runningpig66
 * @date 2025/12/2 周二
 * @time 18:09
 * §6.3 集合与数组 §6.3.4 作为平台类型的集合
 * P.172 代码清单 6.26 FileContentProcessor 的 Kotlin 实现
 *
 * 当你重写或者实现签名中有集合类型的 Java 方法时，像平台类型的可空性一样，
 * 你需要决定使用哪一种 Kotlin 类型来表示这个 Java 类型，它来自你要重写或实现的方法。
 *
 * 这种情况下，你要做出多种选择，它们都会反映在产生的 Kotlin 参数类型中：
 * - 集合是否可空？
 * - 集合中的元素是否可空？
 * - 你的方法会不会修改集合？
 */
// 为了看到差异，考虑下面这些情况。在第一个例子中，一个 Java 接口表示一个能处理文件中文本的对象。
// 这个接口的 Kotlin 实现需要做出下面的选择：
// - 列表将是可空的，因为有些文件是二进制格式，它们的内容不能被表示成文本。
// - 列表中的元素将会是非空的，因为文件中每一行都永远不为 null.
// - 列表将是只读的，因为它表示的是文件的内容，而且这些内容不会被修改。
// 下面就是这种实现看起来的样子。
class FileIndexer : FileContentProcessor {
    override fun processContents(
        path: File,
        binaryContents: ByteArray?,
        textContents: List<String>?
    ) {
    }
}
