import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ProducerConsumerJava {
    static final int MAX_CAPACITY = 10; // Максимальна місткість сховища
    static final int PRODUCTION_COUNT = 100; // Загальна кількість продукції

    static BlockingQueue<Integer> storage = new ArrayBlockingQueue<>(MAX_CAPACITY);

    public static void main(String[] args) {
        // Створюємо та запускаємо виробників і споживачів
        for (int i = 0; i < 5; i++) {
            new Thread(new Producer()).start();
            new Thread(new Consumer()).start();
        }
    }

    static class Producer implements Runnable {
        public void run() {
            for (int i = 0; i < PRODUCTION_COUNT; i++) {
                try {
                    storage.put(i); // Додаємо продукцію у сховище
                    System.out.println("Producer produced: " + i);
                    Thread.sleep(100); // Штучне затримка для демонстрації
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    static class Consumer implements Runnable {
        public void run() {
            for (int i = 0; i < PRODUCTION_COUNT; i++) {
                try {
                    int item = storage.take(); // Беремо продукцію зі сховища
                    System.out.println("Consumer consumed: " + item);
                    Thread.sleep(100); // Штучне затримка для демонстрації
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}