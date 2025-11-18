package ch03;

// join.kt 使用注解 @file:JvmName("StringFunctions") 修改了文件类名，旧的 import 导入失效
//import strings.JoinKt;

import strings.StringFunctions;
import strings._3_3_ExtensionsKt;

import java.util.Arrays;
import java.util.List;

/**
 * @author runningpig66
 * @date 2025/11/19 周三
 * @time 2:48
 * 3.2 让函数更好调：3.2.3 消除静态工具类：顶层函数和属性
 * P.51 代码清单 3.3 声明 joinToString() 作为顶层函数 的 Java 测试
 * <p>
 * 3.3 给别人的类添加方法：扩展函数和属性：3.3.2 从 Java 中调用扩展函数
 * P.55 代码清单
 * <p>
 * 实质上，扩展函数是静态函数，它把调用对象作为了它的第一个参数。调用扩展函数，不会创建适配的对象或者任何运行时的额外消耗。
 * 这使得从 Java 中调用 Kotlin 的扩展函数变得非常简单：调用这个静态函数，然后把接收者对象作为第一个参数传进去即可。
 * 和其他顶层函数一样，包含这个函数的 Java 类的名称，是由这个函数声明的文件名称决定的：
 * 这里的 3.3_Extensions.kt 被编译为 _3_3_ExtensionsKt.java
 */
public class JoinToStringTest {
    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3);
        // [1] join.kt 使用注解 @file:JvmName("StringFunctions") 修改文件类名之前
        // System.out.println(JoinKt.joinToString(list, ", ", "", ""));
        // [2] join.kt 使用注解 @file:JvmName("StringFunctions") 修改文件类名之后
        System.out.println(StringFunctions.joinToString(list, ", ", "", ""));

        char c = _3_3_ExtensionsKt.lastChar("Java");
        System.out.println(c);
    }
}
/* Output:
1, 2, 3
a
 */
