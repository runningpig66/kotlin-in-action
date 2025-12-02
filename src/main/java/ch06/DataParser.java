package ch06;

import java.util.List;

/**
 * @author runningpig66
 * @date 2025/12/2 周二
 * @time 18:06
 * §6.3 集合与数组 §6.3.4 作为平台类型的集合
 * P.172 代码清单 6.27 另一个使用集合参数的 Java 接口
 */
// 把它和另外一个接口对比。这里接口的实现从文本表单中解析出数据并放到一个对象列表中，再把这些对象附加到输出列表中。
// 当发现解析错误时，就把错误信息添加到另一个单独的列表中，作为错误日志。这种情况下的选择是不同的：
// - List<String> 将是非空的，因为调用者总是需要接收错误消息。
// - 列表中的元素将是可空的，因为不是每个输出列表中的条目都有关联的错误信息。
// - List<String> 将是可变的，因为实现代码需要向其中添加元素。
public interface DataParser<T> {
    void parseData(String input,
                   List<T> output,
                   List<String> errors);
}
