package org.example.concurrency.sync;

import com.google.common.collect.Sets;
import jakarta.persistence.criteria.CriteriaBuilder;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;

public class ConcurentHashMapExample {

    private static Map<Integer, Integer> map = new ConcurrentHashMap<>();

    private static Set<Integer> set = ConcurrentHashMap.newKeySet();

    private static Vector<Integer> vector = new Vector<>();

    public static void main(String[] args) {

        while (true) {


            for (int i = 0; i < 10; i++) {
                vector.add(i);
            }

            Thread t1 = new Thread(() -> {
                for (int i = 0; i < vector.size(); i++) {
                    vector.remove(i);
                }
            });

            Thread t2 = new Thread(() -> {
                for (int i = 0; i < vector.size(); i++) {
                    vector.get(i);
                }
            });

            t1.start();
            t2.start();

        }
    }
}
