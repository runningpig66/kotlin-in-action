package ch08;

import static ch08.ProcessTheAnswer.ProcessTheAnswer.processTheAnswer;

/**
 * @author runningpig66
 * @date 2025/12/13 周六
 * @time 22:0
 * §8.1 声明高阶函数 §8.1.3 在 Java 中使用函数类
 * P.211 代码清单
 * <p>
 * 在 Java 中可以很简单地调用使用了函数类型的 Kotlin 函数。Java 8 的 lambda 会被自动转换为函数类型的值。
 */
public class ProcessTheAnswerLambda {
    public static void main(String[] args) {
        processTheAnswer(number -> number + 1);
    }
}
/* Output:
43
 */
