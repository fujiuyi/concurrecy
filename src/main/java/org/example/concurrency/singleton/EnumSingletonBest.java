package org.example.concurrency.singleton;


/**
 * 这就是最好的单例模式，因为枚举天然完成了单例的实现
 * 代码简洁。
 * 线程安全。
 * 防止反射攻击。
 * 防止序列化问题。
 */
public enum EnumSingletonBest {

    INSTANCE;

    public void doSomething(){

    }

    public static void main(String[] args) {
        System.out.println(EnumSingletonBest.INSTANCE.hashCode());
        System.out.println(EnumSingletonBest.INSTANCE.hashCode());
    }
}
