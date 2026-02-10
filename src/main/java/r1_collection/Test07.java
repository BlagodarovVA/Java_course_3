package main.java.r1_collection;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

public class Test07 {
    public static void main(String[] args) {
        Map<String, String> translations = new HashMap<>();
        translations.put("кошка", "cat");
        translations.put("собака", "dog");
        translations.put("слон", "elephant");

        for(Map.Entry<String, String> entry : translations.entrySet()){
            // порядок добавление может не совпадать с порядком вывода
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }


        System.out.println("--------------------------");
        Map<Integer, String> hashMap = new HashMap<>();             // нет порядка
        Map<Integer, String> linkedHashMap = new LinkedHashMap<>(); // сохранен порядок добавления
        Map<Integer, String> treeMap = new TreeMap<>();             // сортируются по ключу

        System.out.println("----- hashMap -----");
        testMap(hashMap);
        System.out.println("----- linkedHashMap -----");
        testMap(linkedHashMap);
        System.out.println("----- treeMap -----");
        testMap(treeMap);

    }

    public static void testMap(Map<Integer, String> map){
        map.put(39, "Bobik");
        map.put(12, "Miha");
        map.put(78, "Tom");
        map.put(0, "Tim");
        map.put(1500, "Lewis");
        map.put(7, "Bobik");

        for (Map.Entry<Integer, String> entry : map.entrySet()){
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }
}
