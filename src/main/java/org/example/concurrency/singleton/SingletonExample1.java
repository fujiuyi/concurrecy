package org.example.concurrency.singleton;

public class SingletonExample1 {

    private SingletonExample1() {

    }

    //代码块和静态的变量是按照顺序执行的，先执行了代码块
    //然后执行instance= null
    //所以会直接打印null
    static {
        instance = new SingletonExample1();
    }

    private static SingletonExample1 instance = null;

    public static SingletonExample1 newInstance() {
        return instance;
    }

    public static void main(String[] args) {
        System.out.println(newInstance());
        System.out.println(newInstance());
    }
}
