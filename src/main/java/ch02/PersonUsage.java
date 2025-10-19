package ch02;

import ch02.person.Person;

/**
 * @author runningpig66
 * @date 2025/10/14 周二
 * @time 3:02
 * P.24 代码清单 2.6 在 Java 中使用 Person 类
 */
public class PersonUsage {
    public static void main(String[] args) {
        Person person = new Person("Bob", true);
        System.out.println(person.getName());
        System.out.println(person.isMarried());
        person.setMarried(false);
    }
}
/* Output:
Bob
true
 */
