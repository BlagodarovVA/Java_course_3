package main.java.r1_collection;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Test004 {
    static void main() {
        List<Integer> linkedList = new LinkedList<>();
        List<Integer> arrayList = new ArrayList<>();

        measureTime(linkedList);
        measureTime(arrayList);
    }

    private static void measureTime(List<Integer> list){
        long start = System.currentTimeMillis();

        // запись в конец листа
        // arrayList - быстрее в 2-3 раза
//        for (int i = 0; i < 1000000; i++) {
//            list.add(i);
//        }

        // чтение
        // arrayList - быстрее в тысячи раз
//        for (int i = 0; i < 100000; i++) {
//            list.get(i);
//        }

        // запись в начало листа
        // linkedList быстрее в 100 раз
//        for (int i = 0; i < 100000; i++) {
//            list.add(0, i);
//        }


        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }
}
