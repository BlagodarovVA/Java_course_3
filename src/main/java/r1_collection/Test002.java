package main.java.r1_collection;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Test002 {

    static void main() {

        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < 15; i++) {
            list.add(i);
        }
        System.out.println(list);

        System.out.println(list.getFirst());
        System.out.println(list.get(12));

        System.out.println(list.size());

        for (Integer item : list) {
            System.out.println(item);
        }

        list.remove(10);
        System.out.println(list);

        // много удалений из листа
        list = new LinkedList<>(list);


    }
}
