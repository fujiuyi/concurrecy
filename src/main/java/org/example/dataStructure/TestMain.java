package org.example.dataStructure;

import java.util.*;

public class TestMain {

    public static void main(String[] args) {

        int size = 100000;
        UnionFind unionFind = new UnionFind(size);

        long strat = System.nanoTime();
        for (int i = 0; i < size; i++) {
            Random random = new Random();
            int p = random.nextInt(size);
            int q = random.nextInt(size);
            unionFind.unionElements(p, q);
        }

        for (int i = 0; i < size; i++) {
            Random random = new Random();
            int p = random.nextInt(size);
            int q = random.nextInt(size);
            unionFind.isConnected(p, q);
        }
        long end = System.nanoTime();

        System.out.println((end - strat) / 1000000000.0);
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
