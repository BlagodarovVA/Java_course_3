package main.java.r1_collection;

import java.util.*;

public class Test12 {
    static void main(String[] args) {

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

        // Без создания класса:
        /*
        numbers.sort(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);
            }
        });
        */

        // замена на лямбда-выражение
        /*
        numbers.sort((o1, o2) -> o2.compareTo(o1));
        */

        // ну тут ваще пиздец короче некуда
        numbers.sort(Comparator.reverseOrder());

        System.out.println(numbers);

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


class BackwardsIntegerComparator implements Comparator<Integer>{
    @Override
    public int compare(Integer o1, Integer o2) {
        if (o1 > o2) return -1;
        if (o1 < o2) return 1;
        return 0;
        // или упрощенно return o2.compareTo(o1);
    }
}
