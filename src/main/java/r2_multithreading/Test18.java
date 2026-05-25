package main.java.r2_multithreading;

import java.util.Scanner;

public class Test18 {
    public static void main(String[] args) {
        MyThread1 myThread1 = new MyThread1();
        myThread1.start();

        Scanner scanner = new Scanner(System.in);
        scanner.nextLine();

        myThread1.shutDown();
    }
}


class MyThread1 extends Thread{
    private volatile boolean running = true;    // volatile - не даст кешировать переменную

    public void run() {
        while (running) {
            System.out.println("Hi");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void shutDown() {
        this.running = false;
    }
}