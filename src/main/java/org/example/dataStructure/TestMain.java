package org.example.dataStructure;

import java.util.*;

public class TestMain {

    public static void main(String[] args) {

        Integer[] arr = new Integer[]{1, 2, 3};
        SegmentTree<Integer> segmentTree = new SegmentTree<>(arr, Integer::sum);
        System.out.println(segmentTree);

        segmentTree.set(0, 5);
        System.out.println(segmentTree);

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
