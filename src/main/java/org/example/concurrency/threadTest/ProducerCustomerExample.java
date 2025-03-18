package org.example.concurrency.threadTest;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
public class ProducerCustomerExample {

    private static final Object lock = new Object();
    private static final List<Integer> list = new ArrayList<>();
    private static final int maxNum = 1;

    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            int count = 0;
            while (true) {
                synchronized (lock) {
                    while (maxNum == list.size()) {
                        try {
                            log.info("生产完毕，开始等待消费者消费");
                            lock.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    ++count;
                    list.add(count);
                    log.info("开始生产 {}", count);

                    lock.notifyAll();
                }
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            while (true) {
                synchronized (lock) {
                    // 如果缓冲区为空，消费者等待
                    while (list.isEmpty()) {
                        try {
                            log.info("消费完毕，开始等待生产者生产");
                            lock.wait();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    log.info("开始消费 {}", list.get(0));
                    list.remove(0);

                    lock.notifyAll();
                }
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });


        thread1.start();
        thread2.start();
    }
}
