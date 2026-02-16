package main.java.r1_collection;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

public class Test08 {
    public static void main(String[] args) {
        Set<String> hashSet = new HashSet<>();              // нет порядка
        Set<String> linkedHashSet = new LinkedHashSet<>();  // порядок ввода
        Set<String> treeSet = new TreeSet<>();              // отсортированы в лексикографическом порядке

        System.out.println("--------- hashSet ----------");
        hashSet.add("Mike");
        hashSet.add("Katy");
        hashSet.add("Tom");
        hashSet.add("Tom");
        hashSet.add("Tom");
        hashSet.add("George");
        hashSet.add("Donald");

//        for(String name : hashSet){
//            System.out.println(name);
//        }

        System.out.println("--------- linkedHashSet ----------");
        linkedHashSet.add("Mike");
        linkedHashSet.add("Katy");
        linkedHashSet.add("Tom");
        linkedHashSet.add("George");
        linkedHashSet.add("Donald");

//        for(String name : linkedHashSet){
//            System.out.println(name);
//        }

        System.out.println("--------- treeSet ----------");
        treeSet.add("Mike");
        treeSet.add("Katy");
        treeSet.add("Tom");
        treeSet.add("George");
        treeSet.add("Donald");

//        for(String name : treeSet){
//            System.out.println(name);
//        }

        System.out.println("--------- contains() ----------");
        System.out.println(hashSet.contains("George"));

        System.out.println("--------- isEmpty() ----------");
        System.out.println(hashSet.isEmpty());









    }
}
