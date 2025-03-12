package org.example.concurrency.atomic;

import lombok.extern.slf4j.Slf4j;
import org.example.concurrency.annoations.NotThreadSafe;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

@Slf4j
@NotThreadSafe
public class AtomicExample {

    private static int maxCount = 5000;

    private static int threadNum = 200;

    private static AtomicInteger count = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {

        Semaphore semaphore = new Semaphore(threadNum);
        CountDownLatch countDownLatch = new CountDownLatch(maxCount);

        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < maxCount; i++) {
            executorService.execute(() -> {
                try {
                    semaphore.acquire();
                } catch (InterruptedException e) {
                    log.warn("[CountExample] exception", e);
                }
                getCountAdd();
                semaphore.release();
                countDownLatch.countDown();
            });
        }
        countDownLatch.await();
        log.info("[CountExample] count {}", count);
    }

    private static int getCountAdd() {
        return count.incrementAndGet();
    }

}
