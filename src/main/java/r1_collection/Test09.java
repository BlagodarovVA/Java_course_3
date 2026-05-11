package main.java.r1_collection;

import java.util.*;

public class Test09 {
    public static void main(String[] args) {
        Map<Integer, String> map = new HashMap<>();
        Set<Integer> set = new HashSet<>();

        map.put(1, "Один");
        map.put(1, "Еще один");

        set.add(1);
        set.add(1);

        System.out.println(map);
        System.out.println(set);


        Map<Person, String> map1 = new HashMap<>();
        Set<Person> set1 = new HashSet<>();

        Person person1 = new Person(1, "Mike");
        Person person2 = new Person(2, "Katy");
        Person person3 = new Person(1, "Mike");

        map1.put(person1, "123");
        map1.put(person2, "123");
        map1.put(person3, "123");

        set1.add(person1);
        set1.add(person2);
        set1.add(person3);

        System.out.println(map1);
        System.out.println(set1);

    }
}


class Person{
    private final int id;
    private final String name;

    public Person(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return id == person.id && Objects.equals(name, person.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}