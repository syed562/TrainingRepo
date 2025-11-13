package com.app.ordermgmnt;

import java.util.Queue;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class ConcurrentOrderSystem {
    private static final int NUM_WORKERS = 5;   // Number of worker threads
    private static final int NUM_ORDERS = 50;   // Total orders to simulate

    public static void main(String[] args) throws InterruptedException {
        // ⿡ Create a thread-safe queue for orders
        Queue<Order> orderQueue = new ConcurrentLinkedQueue<>();

        // ⿢ Create a fixed thread pool with 5 worker threads
        ExecutorService executor = Executors.newFixedThreadPool(NUM_WORKERS);

        // ⿣ Create a counter to track processed orders
        AtomicInteger processedCount = new AtomicInteger(0);

        // ⿤ Submit worker threads to process orders
        for (int i = 0; i < NUM_WORKERS; i++) {
            executor.submit(() -> {
                while (true) {
                    Order order = orderQueue.poll();  // fetch next order
                    if (order != null) {
                        processOrder(order);
                        processedCount.incrementAndGet();
                    }
                    if (processedCount.get() >= NUM_ORDERS) break;
                }
            });
        }

        // ⿥ Simulate thousands of incoming orders
        for (int i = 1; i <= NUM_ORDERS; i++) {
            orderQueue.add(new Order(i, Math.random() * 1000));
        }

        // ⿦ Wait for all tasks to complete
        executor.shutdown();
        executor.awaitTermination(10, TimeUnit.SECONDS);

        System.out.println("\n✅ All orders processed successfully!");
    }

    // ⿧ Simulate order processing logic
    private static void processOrder(Order order) {
        System.out.println(Thread.currentThread().getName() + " processing " + order);
        try {
            Thread.sleep(100); // simulate payment/inventory update time
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    
}
