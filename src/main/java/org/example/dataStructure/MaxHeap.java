package org.example.dataStructure;

import java.util.ArrayList;
import java.util.List;

public class MaxHeap<T extends Comparable<T>> {

    private List<T> data;

    public MaxHeap() {
        this.data = new ArrayList<>();
    }

    public MaxHeap(List<T> data) {
        this.data = new ArrayList<>(data);
        for (int i = data.size() - 1; i >= 0; i--) {
            siftDown(i);
        }
    }

    // 返回堆中的元素个数
    public int size() {
        return data.size();
    }

    // 返回一个布尔值, 表示堆中是否为空
    public boolean isEmpty() {
        return data.isEmpty();
    }

    private int parent(int index) {
        if (index == 0) {
            throw new IllegalArgumentException("index is 0;");
        }
        return (index - 1) / 2;
    }

    private int leftChild(int index) {
        return 2 * index + 1;
    }

    private int rightChild(int index) {
        return 2 * index + 2;
    }

    private void swap(int i, int j) {
        if (i < 0 || j < 0 || i >= data.size() || j >= data.size()) {
            throw new IllegalArgumentException("index error;");
        }
        T temp = data.get(i);
        data.set(i, data.get(j));
        data.set(j, temp);
    }

    // 向堆中添加元素
    public void add(T e) {
        data.add(e);
        siftUp(data.size() - 1);
    }

    private void siftUp(int k) {
        while (k > 0 && data.get(k).compareTo(data.get(parent(k))) > 0) {
            swap(k, parent(k));
            k = parent(k);
        }
    }

    // 看堆中的最大元素
    public T findMax() {
        if (data.isEmpty()) {
            return null;
        }
        return data.get(0);
    }

    // 取出堆中最大元素
    public T remove() {
        T max = findMax();
        //直接跟最后一个值交换位置
        swap(0, data.size() - 1);
        data.remove(data.size() - 1);
        siftDown(0);
        return max;
    }

    private void siftDown(int k) {

        while (leftChild(k) < data.size()) {
            //最上边的元素直接跟子节点最大的交换位置
            int index = leftChild(k);
            if (rightChild(k) < data.size() && data.get(rightChild(k)).compareTo(data.get(leftChild(k))) > 0) {
                index++;
            }
            if (data.get(k).compareTo(data.get(index)) > 0) {
                break;
            }
            swap(k, index);
            k = index;
        }
    }

    // 取出堆中的最大元素，并且替换成元素e
    public T replace(T e) {
        T max = findMax();
        data.set(0, e);
        siftDown(0);
        return max;
    }

    @Override
    public String toString() {
        return "MaxHeap{" +
                "data=" + data +
                '}';
    }
}
