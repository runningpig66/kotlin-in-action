package ch05;

/**
 * @author runningpig66
 * @date 2025/11/29 周六
 * @time 14:56
 * §5.4 使用 Java 函数式接口 §5.4.1 把 lambda 当作参数传递给 Java 方法
 * P.128 代码清单
 */
public class RunnableTest {
    public static void postponeComputation(int delay, Runnable computation) {
        try {
            Thread.sleep(delay);
            computation.run();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
