package ch06;

/**
 * @author runningpig66
 * @date 2025/12/1 周一
 * @time 19:10
 * §6.1 可空性 §6.1.11 可空性和 Java: 平台类型
 * P.154 代码清单 6.15 没有可空性注解的 Java 类
 */
public class Person {
    private final String name;

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
