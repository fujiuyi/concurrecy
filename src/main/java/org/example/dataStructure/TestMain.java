package org.example.dataStructure;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

public class TestMain {

    public static void main(String[] args) {

        LinkedQueue<Integer> arrayExample = new LinkedQueue<>();

        for (int i = 0; i < 10; i++) {
            arrayExample.enQueue(i);
        }
        System.out.println(arrayExample);

        arrayExample.deQueue();
        arrayExample.deQueue();
        System.out.println(arrayExample);

        arrayExample.deQueue();
        arrayExample.deQueue();
        System.out.println(arrayExample);

//        TestMain testMain = new TestMain();
//        testMain.calculate(new ArrayQueue<>(), 100000);
//        testMain.calculate(new LoopQueue<>(), 100000);
//        testMain.calculate(new LinkedQueue<>(), 100000);

//        LinkedListExample<Integer> linkedListExample = new LinkedListExample<>();
//        linkedListExample.addFirst(1);
//        System.out.println(linkedListExample);
//        linkedListExample.addFirst(2);
//        System.out.println(linkedListExample);
//        linkedListExample.addFirst(3);
//        System.out.println(linkedListExample);
//        linkedListExample.addFirst(4);
//        System.out.println(linkedListExample);
//        linkedListExample.addFirst(5);
//        System.out.println(linkedListExample);
//        linkedListExample.add(2, 666);
//        System.out.println(linkedListExample);
//        linkedListExample.remove(2);
//        System.out.println(linkedListExample);
//        linkedListExample.removeFirst();
//        System.out.println(linkedListExample);

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

    public void calculate(LinkedQueue<Integer> linkedQueue, int count) {

        double start = System.nanoTime();

        for (int i = 0; i < count; i++) {
            linkedQueue.enQueue(new Random().nextInt(Integer.MAX_VALUE));
        }
        for (int i = 0; i < count; i++) {
            linkedQueue.deQueue();
        }

        double stop = System.nanoTime();

        System.out.println((stop - start) / 1000000000.0);
    }
}
