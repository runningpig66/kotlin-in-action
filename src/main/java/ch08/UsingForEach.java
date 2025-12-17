package ch08;

import kotlin.Unit;
import kotlin.collections.CollectionsKt;

import java.util.ArrayList;
import java.util.List;

/**
 * @author runningpig66
 * @date 2025/12/13 周六
 * @time 22:41
 * §8.1 声明高阶函数 §8.1.3 在 Java 中使用函数类
 * P.212 代码清单
 * <p>
 * 在 Java 中可以很容易地使用 Kotlin 标准库中以 lambda 作为参数的扩展函数。
 * 但是要注意它们看起来并没有 Kotlin 中那么直观————必须要显式地传递一个接收者对象作为第一个参数：
 * <p>
 * 在 Java 中，函数或者 lambda 可以返回 Unit。但因为在 Kotlin 中 Unit 类型是有一个值的，所以需要显式地返回它。
 * 一个返回 void 的 lambda 不能作为返回 Unit 的函数类型的实参，就像下面的例子中的 (String) -> Unit。
 */
public class UsingForEach {
    public static void main(String[] args) {
        List<String> strings = new ArrayList<>();
        strings.add("42");
        // 可以在 Java 代码中使用 Kotlin 标准库中的函数
        CollectionsKt.forEach(strings, (String s) -> {
            System.out.println(s);
            // 必须要显式地返回一个 Unit 类型的值
            return Unit.INSTANCE;
        });
    }
}
/* Output:
42
 */
