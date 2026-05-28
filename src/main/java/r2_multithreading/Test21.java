package main.java.r2_multithreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Test21 {
    public static void main(String[] args) throws InterruptedException {
        // создаём пул потоков, а не 2 потока явным образом
        try (ExecutorService executorService = Executors.newFixedThreadPool(2)) {

            for (int i = 0; i < 5; i++) {
                executorService.submit(new Work(i));
            }
            // перестаем принимать новые задания, выполняем переданные
            executorService.shutdown();
            System.out.println("All tasks started...");

            // сколько ждать завершения потоков
            boolean terminated = executorService.awaitTermination(1, TimeUnit.HOURS);
            // вывод результата, успели ли завершиться все таски
            System.out.println("All tasks submitted: " + terminated);
        }
    }
}


class Work implements Runnable {
    private final int id;

    public Work(int id){
        this.id = id;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Work " + id + " was completed");
    }
}