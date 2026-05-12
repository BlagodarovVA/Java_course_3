package main.java.r1_collection;

import java.util.*;

public class Test13 {
    public static void main(String[] args) {

        List<Person2> person2List = new ArrayList<>();
        Set<Person2> person2Set = new TreeSet<>();

        addElements(person2List);
        addElements(person2Set);

        Collections.sort(person2List);

        System.out.println(person2List);
        System.out.println(person2Set);


    }

    private static void addElements(Collection<Person2> collection){
        collection.add(new Person2(3, "Lobzik"));
        collection.add(new Person2(1, "Bobik"));
        collection.add(new Person2(4, "Baikonur"));
        collection.add(new Person2(2, "Tom"));
    }
}


class Person2 implements Comparable<Person2>{
    private final int id;
    String name;

    public Person2(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Person2{" +
                "id=" + id +
                ", name='" + name + '\'' +
                "}\n";
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Person2 person2 = (Person2) o;
        return id == person2.id && Objects.equals(name, person2.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public int compareTo(Person2 o) {
        return Integer.compare(name.length(), o.name.length());
    }
}






