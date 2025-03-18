package org.example.dataStructure;

public class ArrayQueue<T> {

    private ArrayExample<T> arrayExample;

    public ArrayQueue(int capacity) {
        this.arrayExample = new ArrayExample<>(capacity);
    }

    public ArrayQueue() {
        this.arrayExample = new ArrayExample<>(10);
    }

    public int getCapacity() {
        return arrayExample.getCapacity();
    }

    public int getSize() {
        return arrayExample.getSize();
    }

    public boolean isEmpty() {
        return arrayExample.getSize() == 0;
    }

    public void enQueue(T element) {
        arrayExample.addLast(element);
    }

    public T deQueue() {
        return arrayExample.removeFirst();
    }

    public T getFront() {
        return arrayExample.get(0);
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append(String.format("ArrayQueue size %d capacity %d \n", getSize(), getCapacity()));
        sb.append("Front [");
        for (int i = 0; i < getSize(); i++) {
            if (i != getSize() - 1) {
                sb.append(arrayExample.get(i)).append(", ");
            } else {
                sb.append(arrayExample.get(i));
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
