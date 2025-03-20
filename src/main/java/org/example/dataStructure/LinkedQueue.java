package org.example.dataStructure;

public class LinkedQueue<T> {

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

    private Node<T> head;
    private Node<T> tail;
    private int size;

    public LinkedQueue() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void enQueue(T element) {
        if (tail == null) {
            tail = new Node<>(element);
            head = tail;
        } else {
            tail.next = new Node<>(element);
            tail = tail.next;
        }
        size++;
    }

    public T deQueue() {
        if (null == head) {
            return null;
        }
        Node<T> returnEle = head;
        head = head.next;
        if (head == null) {
            tail = null;
        }
        return returnEle.ele;
    }

    public T getFront() {
        if (null == head) {
            return null;
        }
        return head.ele;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append(String.format("LinkedQueue size %d \n", getSize()));
        sb.append("Front [");
        Node<T> cur = head;
        while (cur != null) {
            sb.append(cur.ele).append("->");
            cur = cur.next;
        }
        sb.append("NULL]");
        return sb.toString();
    }
}
