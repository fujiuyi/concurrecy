package org.example.concurrency.singleton;

public class LazySingleton {

    private LazySingleton() {

    }

    private static volatile LazySingleton singleton = null;

    public static LazySingleton getSingleton() {
        if (null == singleton) {
            synchronized (LazySingleton.class) {
                if (null == singleton) {
                    singleton = new LazySingleton();
                }
            }
        }
        return singleton;
    }

    public static void main(String[] args) {
        System.out.println(getSingleton());
        System.out.println(getSingleton());
    }
}
