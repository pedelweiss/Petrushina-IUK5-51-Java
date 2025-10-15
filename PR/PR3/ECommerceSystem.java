import java.util.Queue;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class ECommerceSystem {

    private final Queue<String> orderQueue = new LinkedList<>();
    private final ReentrantLock lock = new ReentrantLock();
    private final AtomicInteger processedOrdersCount = new AtomicInteger(0);
    private final ExecutorService consumerPool = Executors.newFixedThreadPool(3); // 3 потока-потребителя

    // Производитель добавляет заказы в очередь
    public void startProducer() {
        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                String order = "Заказ #" + i;
                lock.lock();
                try {
                    orderQueue.add(order);
                    System.out.println("ПРОИЗВОДИТЕЛЬ: Добавлен '" + order + "'");
                } finally {
                    lock.unlock();
                }
                try {
                    Thread.sleep(200); // Имитация времени между заказами
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        }).start();
    }

    // Потребители обрабатывают заказы
    public List<Future<String>> startConsumers() {
        List<Future<String>> futures = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Callable<String> consumerTask = () -> {
                while (processedOrdersCount.get() < 10) { // Работаем, пока все 10 заказов не обработаны
                    String order = null;
                    lock.lock();
                    try {
                        if (!orderQueue.isEmpty()) {
                            order = orderQueue.poll();
                        }
                    } finally {
                        lock.unlock();
                    }

                    if (order != null) {
                        System.out.println("ПОТРЕБИТЕЛЬ (" + Thread.currentThread().getName() + "): Обрабатывает '" + order + "'");
                        Thread.sleep(1000); // Имитация обработки
                        processedOrdersCount.incrementAndGet();
                        return "Результат для '" + order + "' готов.";
                    }
                }
                return "Потребитель завершил работу.";
            };
            futures.add(consumerPool.submit(consumerTask));
        }
        return futures;
    }

    public void shutdown() throws InterruptedException {
        consumerPool.shutdown();
        consumerPool.awaitTermination(10, TimeUnit.SECONDS);
        System.out.println("\nОбщее количество обработанных заказов: " + processedOrdersCount.get());
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ECommerceSystem system = new ECommerceSystem();

        system.startProducer();
        List<Future<String>> results = system.startConsumers();

        // Ждем и выводим результаты от потребителей
        for (Future<String> future : results) {
            System.out.println("РЕЗУЛЬТАТ: " + future.get());
        }

        system.shutdown();
    }
}