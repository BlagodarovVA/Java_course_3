package main.java.r1_collection;

import java.util.Stack;

public class Test15 {
    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        // стек работает противоположно очереди

        stack.push(5);
        stack.push(3);
        stack.push(1);

        // peek получает последний элемент не удаляя
        System.out.println(stack.peek());
        // pop получает и удаляет последний элемент из стека
        System.out.println(stack.pop());

        // проверка, что стек пустой
        System.out.println(stack.empty());

        while (!stack.empty()) {
            System.out.println(stack.pop());
        }

    }
}
