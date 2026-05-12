package main.java.r1_collection;

import java.util.*;

public class Test12 {
    public static void main(String[] args) {

        List<String> animals = new ArrayList<>();
        animals.add("monkey");
        animals.add("dog");
        animals.add("elephant");
        animals.add("bird");
        animals.add("au");

        System.out.println(animals);
        animals.sort(new StringLengthComparator()); // в скобках объект класса компаратора
        System.out.println(animals);

        List<Integer> numbers = new ArrayList<>();
        numbers.add(5);
        numbers.add(0);
        numbers.add(500);
        numbers.add(100);

        System.out.println(numbers);

        // Без создания класса, компаратор как параметр для метода сравнения:
        /*
        numbers.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);
            }
        });
        */

        // замена на лямбда-выражение, короче
        /*
        numbers.sort((o1, o2) -> o2.compareTo(o1));
        */

        // ну тут ваще пиздец короче некуда
        numbers.sort(Comparator.reverseOrder());
        System.out.println(numbers);


        List<Person1> people = new ArrayList<>();
        people.add(new Person1(3, "Sharik"));
        people.add(new Person1(1, "Bobik"));
        people.add(new Person1(2, "Tuzik"));

        System.out.println(people);
        people.sort(new Comparator<Person1>() {
            @Override
            public int compare(Person1 o1, Person1 o2) {
                if (o1.getId() > o2.getId()) return 1;
                if (o1.getId() < o2.getId()) return -1;
                return 0;
            }
        });
        System.out.println(people);
    }
}


class StringLengthComparator implements Comparator<String>{
    @Override
    public int compare(String o1, String o2) {
        /*
            o1 > o2 => 1
            o1 < o2 => -1
            o1 == o2 => 0
         */
        if (o1.length() > o2.length()) return 1;
        if (o1.length() < o2.length()) return -1;
        return 0;
        // или упрощенно return Integer.compare(o1.length(), o2.length());
    }
}


class Person1 {
    private int id;
    String name;

    public Person1(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person1{" +
                "id=" + id +
                ", name='" + name + '\'' +
                "}\n";
    }

    public int getId() {
        return id;
    }
}