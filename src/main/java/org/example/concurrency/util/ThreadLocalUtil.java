package org.example.concurrency.util;

public class ThreadLocalUtil {

    private final static ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public static String getThreadLocal() {
        return threadLocal.get();
    }

    public static void setThreadLocal(String param) {
        threadLocal.set(param);
    }

    public static void removeThreadLocal() {
        threadLocal.remove();
    }
}
