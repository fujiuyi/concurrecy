package org.example.concurrency.threadTest;

import lombok.extern.slf4j.Slf4j;

import java.util.Random;
import java.util.concurrent.SynchronousQueue;

@Slf4j
public class BlockingQueueExample {

    private static final SynchronousQueue<Integer> synchronousQueue = new SynchronousQueue<>();

    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            while (true) {
                log.info("生产完毕，开始等待消费者消费");
                try {
                    synchronousQueue.put(new Random().nextInt(10));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            while (true) {
                try {
                    Integer value = synchronousQueue.take();
                    log.info("消费完毕，开始等待生产者生产 res {}", value);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });


        thread1.start();
        thread2.start();
    }
}
