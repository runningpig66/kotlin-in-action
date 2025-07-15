package ch04;

import ch04.ex4_1_1_ObjectDeclarations.CaseInsensitiveFileComparator;
import ch04.ex4_1_2_ObjectDeclarations1.Person;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ObjectTest {
    public static void main(String[] args) {
        System.out.println(CaseInsensitiveFileComparator.INSTANCE.compare(
                new File("/User"), new File("/user")));

        List<Person> persons = new ArrayList<>(Arrays.asList(new Person("Bob"), new Person("Alice")));
        persons.stream()
                .sorted(Person.NameComparator.INSTANCE)
                .forEach(System.out::println);
    }
}
/**
 * 0
 * Person(name=Alice)
 * Person(name=Bob)
 */