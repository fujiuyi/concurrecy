package org.example.concurrency.threadTest;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

@Slf4j
public class FutureTaskExample {

    public static void main(String[] args) throws Exception {

        FutureTask<String> futureTask = new FutureTask<>(() -> {
            log.info("callable run");
            Thread.sleep(5000);
            return "OK";
        });

        ExecutorService service = Executors.newCachedThreadPool();
        service.execute(futureTask);
        log.info("main run wait");
        Thread.sleep(2000);
        log.info("future result {}",futureTask.get());
        service.shutdown();
    }
}
