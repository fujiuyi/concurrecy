package org.example.concurrency.singleton;

public class EnumSingleton {

    private EnumSingleton() {

    }

    private static EnumSingleton singleton = null;

    public static EnumSingleton getInstance() {
        return Singleton.SINGLETON.getInstance();
    }

    private enum Singleton {
        SINGLETON;

        Singleton() {
            singleton = new EnumSingleton();
        }

        public EnumSingleton getInstance() {
            return singleton;
        }
    }
}
