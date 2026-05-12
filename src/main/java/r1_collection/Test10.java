package main.java.r1_collection;

import java.util.Objects;

public class Test10 {
    public static void main(String[] args) {



    }
}


class Contact {
    private final int id;
    private final String name;

    public Contact(int id, String name) {
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

    // hashCode - быстрый метод
    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    // equals - медленный метод
    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Contact contact = (Contact) o;
        return id == contact.id && Objects.equals(name, contact.name);
    }
}