package org.example.dataStructure;

public class UnionFind {

    private int[] parent;
    //对应行上的高度
    private int[] sz;

    public UnionFind(int size) {
        this.parent = new int[size];
        this.sz = new int[size];
        for (int i = 0; i < size; i++) {
            this.parent[i] = i;
            this.sz[i] = 1;
        }
    }

    //找到指定位置的父节点，父节点的序号和值是相等的
    private int find(int index) {

        while (index != this.parent[index]) {
            this.parent[index] = this.parent[this.parent[index]];
            index = parent[index];
        }
        return index;
    }

    public int getSize() {
        return this.parent.length;
    }

    public boolean isConnected(int p, int q) {
        return find(p) == find(q);
    }

    public void unionElements(int p, int q) {
        int pRoot = find(p);
        int qRoot = find(q);

        if (pRoot == qRoot) {
            return;
        }

        if (this.sz[pRoot] > this.sz[qRoot]) {
            this.parent[qRoot] = pRoot;
        } else if (this.sz[pRoot] < this.sz[qRoot]) {
            this.parent[pRoot] = qRoot;
        } else {
            this.parent[qRoot] = pRoot;
            this.sz[pRoot] = this.sz[pRoot] + 1;
        }

    }
}
