package org.example.concurrency.immutable;

import com.google.common.collect.Maps;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class ImmutableExample {

    private final Map<Integer, Integer> map = Maps.newHashMap();

    private static final List<String> list = Collections.unmodifiableList(Collections.singletonList("123"));

    public static void main(String[] args) {
        list.add("1123");
        System.out.println(list);
    }
}
