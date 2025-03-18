package org.example.concurrency.sync;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
public class LockExample {

    private static final ReentrantLock lock = new ReentrantLock();

    private static final Condition condtion = lock.newCondition();

    public static void main(String[] args) {
        Thread thread1 = new Thread(() -> {
            lock.lock();
            log.info("thread A get lock");
            try {
                Thread.sleep(1000);
                condtion.await();
                log.info("thread A await and start");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                lock.unlock();
            }

        });

        Thread thread2 = new Thread(() -> {
            lock.lock();
            log.info("thread B get lock");
            try {
                Thread.sleep(5000);
                condtion.signal();
                log.info("thread B signal and start");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                lock.unlock();
            }

        });

        thread1.start();
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        thread2.start();
    }
}
