package main.java.r2_multithreading;

public class Test19 {
    private int counter;

    public static void main(String[] args) throws InterruptedException {
        Test19 test19 = new Test19();
        test19.doWork();
    }

    // synchronized в 1 момент времени доет доступ к методу только 1 потоку
    public synchronized void increment(){
        counter++;
    }

    public void doWork() throws InterruptedException {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10000; i++) {
//                    counter++;
                    increment();
                }
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10000; i++) {
//                    counter++;
                    increment();
                }
            }
        });

        // запуск потока
        thread1.start();
        thread2.start();

        // ожидание завершения потока. Не решает проблему гонки потоков
        thread1.join();
        thread2.join();

        //Thread.sleep(100);
        System.out.println(counter);
    }
}

