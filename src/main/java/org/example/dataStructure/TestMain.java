package org.example.dataStructure;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Random;

public class TestMain {

    public static void main(String[] args) {

//        ArrayExample<Integer> arrayExample = new ArrayExample<>(10);
//
//        for (int i = 0; i < 10; i++) {
//            arrayExample.add(i, i);
//        }
//        System.out.println(arrayExample);
//
//        arrayExample.addFirst(18);
//        System.out.println(arrayExample);
//
//        arrayExample.addLast(12);
//        System.out.println(arrayExample);
//
//        arrayExample.removeFirst();
//        arrayExample.removeLast();
//        System.out.println(arrayExample);
//
//        arrayExample.remove(5);
//        arrayExample.remove(5);
//        System.out.println(arrayExample);

        TestMain testMain = new TestMain();
//        testMain.calculate(new ArrayQueue<>(), 100000);
        testMain.calculate(new LoopQueue<>(), 10000000);
        testMain.calculate(new ArrayDeque<>(), 10000000);

    }

    public void calculate(ArrayQueue<Integer> arrayQueue, int count) {

        long start = System.nanoTime();

        for (int i = 0; i < count; i++) {
            arrayQueue.enQueue(new Random().nextInt(Integer.MAX_VALUE));
        }
        for (int i = 0; i < count; i++) {
            arrayQueue.deQueue();
        }

        long stop = System.nanoTime();

        System.out.println((stop - start) / 1000000000);
    }

    public void calculate(LoopQueue<Integer> loopQueue, int count) {

        double start = System.nanoTime();

        for (int i = 0; i < count; i++) {
            loopQueue.enQueue(new Random().nextInt(Integer.MAX_VALUE));
        }
        for (int i = 0; i < count; i++) {
            loopQueue.deQueue();
        }

        double stop = System.nanoTime();

        System.out.println((stop - start) / 1000000000.0);
    }

    public void calculate(ArrayDeque<Integer> arrayDeque, int count) {

        double start = System.nanoTime();

        for (int i = 0; i < count; i++) {
            arrayDeque.push(new Random().nextInt(Integer.MAX_VALUE));
        }
        for (int i = 0; i < count; i++) {
            arrayDeque.pop();
        }

        double stop = System.nanoTime();

        System.out.println((stop - start) / 1000000000.0);
    }
}
