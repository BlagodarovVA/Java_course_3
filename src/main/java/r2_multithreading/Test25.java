package main.java.r2_multithreading;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Test25 {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch cdl = new CountDownLatch(3);     // через 3 отсчета отопрется защелка

        ExecutorService executorService = Executors.newFixedThreadPool(3);

        for (int i = 0; i < 3; i++) {
            executorService.submit(new Processor(i, cdl));
        }

        executorService.shutdown();

//        cdl.await();
//        System.out.println("Защелка открыта, выполняется основной поток");

        for (int i = 0; i < 3; i++) {
            Thread.sleep(1000);
            cdl.countDown();
        }
    }
}


class Processor implements Runnable {
    private int id;
    private final CountDownLatch countDownLatch;

    public Processor(int id, CountDownLatch countDownLatch){
        this.id = id;
        this.countDownLatch = countDownLatch;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Поток с id " + id + " выполняется");
    }
}