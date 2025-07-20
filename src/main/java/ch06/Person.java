package ch06;

// 代码清单 6.15 没有可空性注解的 Java 类
public class Person {
    private final String name;

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}