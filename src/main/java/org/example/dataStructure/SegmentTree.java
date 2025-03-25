package org.example.dataStructure;

public class SegmentTree<T> {

    private T[] data;
    private T[] segmentTree;
    private Merger<T> merger;

    public SegmentTree(T[] arr, Merger<T> merge) {
        this.merger = merge;

        this.data = (T[]) new Object[arr.length];
        System.arraycopy(arr, 0, this.data, 0, arr.length);

        this.segmentTree = (T[]) new Object[4 * arr.length];
        buildSegmentTree(0, 0, arr.length - 1);
    }

    // 在treeIndex的位置创建表示区间[l...r]的线段树
    private void buildSegmentTree(int treeIndex, int l, int r) {
        //如果左右相等的时候就证明已经找到最终的节点了，直接将原数组中位置值复制到线段树上
        if (l == r) {
            this.segmentTree[treeIndex] = this.data[l];
            return;
        }
        //避免整数溢出
        int leftChild = leftChild(treeIndex);
        int rightChild = rightChild(treeIndex);

        int mid = l + (r - l) / 2;

        buildSegmentTree(leftChild, l, mid);
        buildSegmentTree(rightChild, mid + 1, r);

        this.segmentTree[treeIndex] = this.merger.merge(this.segmentTree[leftChild], this.segmentTree[rightChild]);
    }

    public int getSize() {
        return data.length;
    }

    public T get(int index) {
        if (index < 0 || index >= data.length) {
            throw new IllegalArgumentException("index error");
        }
        return data[index];
    }

    // 返回完全二叉树的数组表示中，一个索引所表示的元素的左孩子节点的索引
    private int leftChild(int index) {
        return 2 * index + 1;
    }

    // 返回完全二叉树的数组表示中，一个索引所表示的元素的右孩子节点的索引
    private int rightChild(int index) {
        return 2 * index + 2;
    }

    // 返回区间[queryL, queryR]的值
    public T query(int queryL, int queryR) {
        if (queryL < 0 || queryL >= data.length ||
                queryR < 0 || queryR >= data.length || queryL > queryR)
            throw new IllegalArgumentException("Index is illegal.");

        return query(0, 0, data.length - 1, queryL, queryR);
    }

    // 在以treeIndex为根的线段树中[l...r]的范围里，搜索区间[queryL...queryR]的值
    private T query(int treeIndex, int l, int r, int queryL, int queryR) {

        if (l == queryL && r == queryR) {
            return this.segmentTree[treeIndex];
        }
        //避免整数溢出
        int leftChild = leftChild(treeIndex);
        int rightChild = rightChild(treeIndex);

        int mid = l + (r - l) / 2;

        if (queryL >= mid + 1) {
            return query(rightChild, mid + 1, r, queryL, queryR);
        } else if (queryR <= mid) {
            return query(leftChild, l, mid, queryL, queryR);
        }

        T left = query(leftChild, l, mid, queryL, mid);
        T right = query(rightChild, mid + 1, r, mid + 1, queryR);
        return this.merger.merge(left, right);

    }

    // 将index位置的值，更新为e
    public void set(int index, T e) {
        if (index < 0 || index >= data.length) {
            throw new IllegalArgumentException("Index is illegal");
        }
        data[index] = e;
        set(0, 0, data.length - 1, index, e);
    }

    // 在以treeIndex为根的线段树中更新index的值为e
    private void set(int treeIndex, int l, int r, int index, T e) {
        if (l == r) {
            this.segmentTree[treeIndex] = e;
            return;
        }
        //避免整数溢出
        int leftChild = leftChild(treeIndex);
        int rightChild = rightChild(treeIndex);

        int mid = l + (r - l) / 2;

        if (index >= mid + 1) {
            set(rightChild, mid + 1, r, index, e);
        } else {
            set(leftChild, l, mid, index, e);
        }
        this.segmentTree[treeIndex] = this.merger.merge(this.segmentTree[leftChild], this.segmentTree[rightChild]);

    }

    @Override
    public String toString() {
        if (segmentTree == null || segmentTree.length == 0) {
            return "Empty Tree";
        }

        StringBuilder sb = new StringBuilder();
        int height = (int) (Math.log(segmentTree.length) / Math.log(2)) + 1;
        int maxLevel = height - 1;

        for (int level = 0; level < height; level++) {
            // 计算当前层的起始和结束索引
            int start = (int) Math.pow(2, level) - 1;
            int end = Math.min((int) Math.pow(2, level + 1) - 1, segmentTree.length);

            // 计算缩进和间距
            int indent = (int) Math.pow(2, (maxLevel - level)) - 1;
            int spacing = (int) Math.pow(2, (maxLevel - level + 1)) - 1;

            // 添加缩进
            sb.append(" ".repeat(indent));

            // 添加当前层的节点
            for (int i = start; i < end; i++) {
                sb.append(segmentTree[i]);
                if (i < end - 1) {
                    sb.append(" ".repeat(spacing));
                }
            }

            sb.append("\n");

            // 如果不是最后一层，添加连接线
            if (level < height - 1) {
                // 添加缩进
                sb.append(" ".repeat(indent - 1));

                // 添加连接线
                int nextLevelStart = (int) Math.pow(2, level + 1) - 1;

                for (int i = 0; i < Math.pow(2, level); i++) {
                    if (nextLevelStart + 2 * i < segmentTree.length) {
                        sb.append("/");
                    } else {
                        sb.append(" ");
                    }

                    int spacesBetween = spacing - 1;
                    sb.append(" ".repeat(spacesBetween));

                    if (nextLevelStart + 2 * i + 1 < segmentTree.length) {
                        sb.append("\\");
                    } else {
                        sb.append(" ");
                    }

                    if (i < Math.pow(2, level) - 1) {
                        sb.append(" ".repeat(spacing - spacesBetween - 1));
                    }
                }

                sb.append("\n");
            }
        }

        return sb.toString();
    }


}

//融合器，用来定义线段树最终的实现作用
interface Merger<T> {

    T merge(T t1, T t2);
}
