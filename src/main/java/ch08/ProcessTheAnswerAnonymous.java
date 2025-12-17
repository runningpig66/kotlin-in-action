package ch08;

import kotlin.jvm.functions.Function1;

import static ch08.ProcessTheAnswer.ProcessTheAnswer.processTheAnswer;

/**
 * @author runningpig66
 * @date 2025/12/13 周六
 * @time 22:08
 * §8.1 声明高阶函数 §8.1.3 在 Java 中使用函数类
 * P.211 代码清单
 * <p>
 * 在旧版的 Java 中，可以传递一个实现了函数接口中的 invoke 方法的匿名类的实例：
 */
public class ProcessTheAnswerAnonymous {
    public static void main(String[] args) {
        processTheAnswer(
                // 在 Java 代码中使用函数类型（Java 8 以前）
                new Function1<Integer, Integer>() {
                    @Override
                    public Integer invoke(Integer number) {
                        System.out.println(number);
                        return number + 1;
                    }
                });
    }
}
/* Output:
42
43
 */
