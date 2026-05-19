package main.java.r1_collection;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class Test14 {
    public static void main(String[] args) {

        Person3 person1 = new Person3(1);
        Person3 person2 = new Person3(2);
        Person3 person3 = new Person3(3);
        Person3 person4 = new Person3(4);

        Queue<Person3> people = new ArrayBlockingQueue<Person3>(4);
        people.add(person3);
        people.add(person2);
        people.add(person4);
        // add - при ошибке добавления выдаст исключение
        // offer - при ошибке добавления выдаст false
        System.out.println(people.add(person1));
        System.out.println(people.offer(person1));

        System.out.println(people);

        // remove/poll удаляет 1 в очереди
        System.out.println("\nremove: " + people.remove());
        // element/peek получает 1 в очереди
        System.out.println("\npeek: " + people.peek() +'\n');

        System.out.println(people);
    }
}


class Person3 {
    private int id;

    public Person3(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "\n{" +
                "id=" + id +
                "}";
    }
}