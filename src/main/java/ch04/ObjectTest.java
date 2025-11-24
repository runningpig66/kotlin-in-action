package ch04;

import ch04.ex4_1_1_ObjectDeclarations.CaseInsensitiveFileComparator;
import ch04.ex4_1_2_ObjectDeclarations1.Person;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author runningpig66
 * @date 2025/11/24 周一
 * @time 0:43
 * §4.4 "object" 关键字：将声明一个类与创建一个实例结合起来 §4.4.1 对象声明：创建单例易如反掌
 * P.97 代码清单
 * <p>
 * Kotlin 中的对象声明被编译成了通过静态字段来持有它的单一实例的类，这个字段名字始终都是 INSTANCE.
 * 如果在 Java 中实现单例模式，你也许也会顺手做同样的事。
 * 因此，要从 Java 代码使用 Kotlin 对象，可以通过访问静态的 INSTANCE 字段。
 */
public class ObjectTest {
    public static void main(String[] args) {
        // Kotlin Code 1: 4.4.1_1_ObjectDeclarations.kt
        // println(CaseInsensitiveFileComparator.compare(File("/User"), File("/user")))
        // val files = listOf(File("/Z"), File("/a"))
        // println(files.sortedWith(CaseInsensitiveFileComparator))

        // Java Code 1:
        System.out.println(
                CaseInsensitiveFileComparator.INSTANCE.compare(
                        new File("/User"), new File("/user")));
        final List<File> files = List.of(new File("/Z"), new File("/a"));
        List<File> sorted = new ArrayList<>(files);
        sorted.sort(CaseInsensitiveFileComparator.INSTANCE);
        System.out.println(sorted);

        // Kotlin Code 2: 4.4.1_2_ObjectDeclarations1.kt
        // val persons = listOf(Person("Bob"), Person("Alice"))
        // println(persons.sortedWith(Person.NameComparator))

        // Java Code 2:
        final List<Person> persons = Arrays.asList(new Person("Bob"), new Person("Alice"));
        List<Person> sortedPersons = new ArrayList<>(persons);
        sortedPersons.sort(Person.NameComparator.INSTANCE);
        System.out.println(sortedPersons);
    }
}
/* Output:
0
[\a, \Z]
[Person(name=Alice), Person(name=Bob)]
 */
