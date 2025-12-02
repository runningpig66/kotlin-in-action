package ch06;

import java.io.File;
import java.util.List;

/**
 * @author runningpig66
 * @date 2025/12/2 周二
 * @time 18:01
 * §6.3 集合与数组 §6.3.4 作为平台类型的集合
 * P.171 代码清单 6.25 使用集合参数的 Java 接口
 */
// 为了看到差异，考虑下面这些情况。在第一个例子中，一个 Java 接口表示一个能处理文件中文本的对象。
// 这个接口的 Kotlin 实现需要做出下面的选择：
// - 列表将是可空的，因为有些文件是二进制格式，它们的内容不能被表示成文本。
// - 列表中的元素将会是非空的，因为文件中每一行都永远不为 null.
// - 列表将是只读的，因为它表示的是文件的内容，而且这些内容不会被修改。
public interface FileContentProcessor {
    void processContents(
            File path,
            byte[] binaryContents,
            List<String> textContents);
}
