package org.example.concurrency.threadTest;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

@Slf4j
public class callableExample {

    static class MyCallable implements Callable<String> {

        @Override
        public String call() throws Exception {
            log.info("callable run");
            Thread.sleep(5000);
            return "OK";
        }
    }


    public static void main(String[] args) throws InterruptedException, ExecutionException {

        ExecutorService service = Executors.newCachedThreadPool();
        Future<String> future = service.submit(new MyCallable());
        log.info("main run wait");
        Thread.sleep(2000);
        log.info("future result {}",future.get());
    }
}
