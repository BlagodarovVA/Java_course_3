package main.java.r2_multithreading;

public class Test17 {
    public static void main(String[] args) throws InterruptedException {
/*
        MyThread myThread = new MyThread();
        myThread.start();

        MyThread myThread1 = new MyThread();
        myThread1.start();

        int time = 2000;
        System.out.println("Начало сна: " + time + " мс");
        Thread.sleep(time); // задержка 2 секунды
        System.out.println("Multithreading main");
*/

        Thread thread = new Thread(new Runner());
        thread.start();
    }
}

class Runner implements Runnable{

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("My thread " + i);
        }
    }
}

class MyThread extends Thread{

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("My thread " + i);
        }
    }
}