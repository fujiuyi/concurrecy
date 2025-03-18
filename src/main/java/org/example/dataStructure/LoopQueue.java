package org.example.dataStructure;

public class LoopQueue<T> {

    private T[] arr;
    private int front = 0;
    private int tail = 0;

    public LoopQueue(int capacity) {
        this.arr = (T[]) new Object[capacity + 1];
        this.front = 0;
        this.tail = 0;
    }

    public LoopQueue() {
        this(10);
    }

    public int getCapacity() {
        return arr.length - 1;
    }

    public int getSize() {
        return front <= tail ? tail - front : tail + arr.length - front;
    }

    public boolean isEmpty() {
        return front == tail;
    }

    public void enQueue(T element) {

        //尾部的标记+1等于头部的标记的时候，就证明已经满了
        if ((tail + 1) % arr.length == front) {
            //因为之前的容量是加1的，因此扩容是之前容量的两倍减一
            resize(arr.length * 2 - 1);
        }

        arr[tail] = element;
        tail = (tail + 1) % arr.length;
    }

    public T deQueue() {
        if (isEmpty()) {
            throw new IllegalArgumentException("queue is empty");
        }
        T ele = arr[front];
        arr[front] = null;
        front = (front + 1) % arr.length;

        if (getSize() == arr.length / 4) {
            //因为之前的容量是加1的，因此扩容是之前容量的两倍减一
            resize((arr.length + 1) / 2);
        }

        return ele;
    }

    public T getFront() {
        if (isEmpty()) {
            throw new IllegalArgumentException("queue is empty");
        }
        return arr[front];
    }

    private void resize(int capacity) {
        T[] newArr = (T[]) new Object[capacity];
        for (int i = 0; i < getSize(); i++) {
            newArr[i] = arr[(front + i) % arr.length];
        }
        arr = newArr;
        front = 0;
        tail = getSize();
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append(String.format("LoopQueue size %d capacity %d \n", getSize(), getCapacity()));
        sb.append("Front [");
        for (int i = front; i != tail; i = (i + 1) % arr.length) {
            if (i != tail - 1) {
                sb.append(arr[i]).append(", ");
            } else {
                sb.append(arr[i]);
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
