package org.example.dataStructure;

public class ArrayExample<T> {

    private T[] arr = null;
    private int size = 0;

    /**
     * 缺省创建10个容量大小的数组
     */
    public ArrayExample() {
        this(10);
    }

    @SuppressWarnings("unchecked")
    public ArrayExample(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("ArrayExample init failed,index err.");
        }
        this.arr = (T[]) new Object[10];
    }

    public void add(int index, T e) {
        if (index < 0 || index > size) {
            throw new IllegalArgumentException("add failed,index err.");
        }
        if (size == arr.length) {
            resize(2 * arr.length);
        }
        for (int i = size - 1; i >= index; i--) {
            arr[i + 1] = arr[i];
        }
        arr[index] = e;
        size++;
    }

    public void addFirst(T e) {
        add(0, e);
    }

    public void addLast(T e) {
        add(size, e);
    }

    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("remove failed,index err.");
        }
        if (size == arr.length / 4 && arr.length / 2 != 0) {
            resize(arr.length / 2);
        }
        T ele = arr[index];
        for (int i = index; i < size - 1; i++) {
            arr[i] = arr[i + 1];
        }
        arr[size - 1] = null;
        size--;
        return ele;
    }

    public T removeFirst() {
        return remove(0);
    }

    public void removeLast() {
        remove(size - 1);
    }

    public void set(int index, T e) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("set failed,index err.");
        }
        arr[index] = e;
    }

    @SuppressWarnings("unchecked")
    private void resize(int capacity) {
        T[] newArr = (T[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            newArr[i] = arr[i];
        }
        arr = newArr;
    }

    public boolean contains(T e) {
        for (int i = 0; i < size; i++) {
            if (arr[i] == e) {
                return true;
            }
        }
        return false;
    }

    public int find(T e) {
        for (int i = 0; i < size; i++) {
            if (arr[i] == e) {
                return i;
            }
        }
        return -1;
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("add failed,index err.");
        }
        return arr[index];
    }

    public int getCapacity() {
        return arr.length;
    }

    public int getSize() {
        return size;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Arr size %d capacity %d \n", size, arr.length));
        sb.append("[");
        for (int i = 0; i < size; i++) {
            if (i != size - 1) {
                sb.append(arr[i]).append(", ");
            } else {
                sb.append(arr[i]);
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
