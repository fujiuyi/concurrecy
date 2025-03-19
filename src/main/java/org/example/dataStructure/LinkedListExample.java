package org.example.dataStructure;

public class LinkedListExample<T> {

    private class Node<T> {
        public T ele;
        public Node<T> next;

        public Node(T ele, Node<T> next) {
            this.ele = ele;
            this.next = next;
        }

        public Node(T ele) {
            this.ele = ele;
        }

        public Node() {
        }

        @Override
        public String toString() {
            return ele.toString();
        }
    }

    //初始化一个虚拟的节点，指向头节点的前一个节点
    private final Node<T> dummyHead = new Node<>(null);
    private int size;

    public LinkedListExample(T head) {
        this.dummyHead.next = new Node<>(head);
        size++;
    }

    public LinkedListExample() {
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void add(int index, T ele) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("index error.");
        }

        Node<T> prev = this.dummyHead;
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }

        prev.next = new Node<>(ele, prev.next);
        size++;
    }

    public void addFirst(T ele) {
        add(0, ele);
    }

    public void addLast(T ele) {
        add(size, ele);
    }

    public void remove(int index) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("index error.");
        }

        Node<T> prev = this.dummyHead;
        for (int i = 0; i < index; i++) {
            prev = prev.next;
        }
        Node<T> cur = prev.next;
        prev.next = cur.next;
        cur.next = null;
        size--;
    }

    public void removeFirst() {
        remove(0);
    }

    public void removeLast() {
        remove(size);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("LinkedListExample size %d \n", getSize()));
        Node<T> cur = this.dummyHead.next;
        while (cur != null) {
            sb.append(cur.ele).append("->");
            cur = cur.next;
        }
        sb.append("NULL");
        return sb.toString();
    }
}
