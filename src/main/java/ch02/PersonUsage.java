package ch02;

import ch02.person.Person;

public class PersonUsage {
    public static void main(String[] args) {
        Person person = new Person("Bob", true);
        System.out.println(person.getName());
        System.out.println(person.isMarried());
        person.setMarried(false);
    }
}