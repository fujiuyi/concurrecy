package org.example.dataStructure;

import org.checkerframework.checker.units.qual.N;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BST<T extends Comparable<T>> {

    private class Node {
        public T value;
        public Node left;
        public Node right;

        public Node(T value) {
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }

    private Node root;
    private int size;

    public BST() {
        this.root = null;
        this.size = 0;
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    // 向二分搜索树中添加新的元素e
    public void add(T ele) {
        root = addRecursion(root, ele);
    }

    private void add(Node root, T ele) {
        if (root.value.equals(ele)) {
            return;
        } else if (root.value.compareTo(ele) > 0 && root.left == null) {
            root.left = new Node(ele);
            size++;
        } else if (root.value.compareTo(ele) < 0 && root.right == null) {
            root.right = new Node(ele);
            size++;
        } else {
            if (root.value.compareTo(ele) > 0) {
                add(root.left, ele);
            } else {
                add(root.right, ele);
            }
        }
    }

    private Node addRecursion(Node root, T ele) {
        if (null == root) {
            size++;
            return new Node(ele);
        }
        if (root.value.compareTo(ele) > 0) {
            root.left = addRecursion(root.left, ele);
        } else if (root.value.compareTo(ele) < 0) {
            root.right = addRecursion(root.right, ele);
        }

        return root;
    }

    public boolean contains(T ele) {
        return contains(this.root, ele);
    }

    private boolean contains(Node root, T ele) {
        if (null == root) {
            return false;
        }

        if (root.value.compareTo(ele) > 0) {
            return contains(root.left, ele);
        } else if (root.value.compareTo(ele) < 0) {
            return contains(root.right, ele);
        } else {
            return true;
        }
    }

    /**
     * 前序遍历
     */
    public void preOrder() {
        preOrder(this.root);
    }

    private void preOrder(Node root) {
        if (root == null) {
            return;
        }
        System.out.print(root.value + ",");
        preOrder(root.left);
        preOrder(root.right);
    }

    public void preOrderNR() {
        if (this.root == null) {
            return;
        }
        Stack<Node> stack = new Stack<>();
        stack.push(this.root);
        while (!stack.isEmpty()) {

            Node cur = stack.pop();
            System.out.println(cur.value);

            if (null != cur.right) {
                stack.push(cur.right);
            }
            if (null != cur.left) {
                stack.push(cur.left);
            }
        }
    }

    /**
     * 中序遍历
     */
    public void inOrder() {
        inOrder(this.root);
    }

    private void inOrder(Node root) {
        if (root == null) {
            return;
        }
        inOrder(root.left);
        System.out.print(root.value + ",");
        inOrder(root.right);
    }

    /**
     * 非递归的中序遍历
     */
    public void inOrderNR() {
        if (this.root == null) {
            return;
        }
        Stack<Node> stack = new Stack<>();
        Node cur = this.root;
        while (cur != null || !stack.isEmpty()) {
            while (cur != null) {
                stack.push(cur);
                cur = cur.left;
            }

            cur = stack.pop();
            System.out.println(cur.value);

            cur = cur.right;
        }
    }

    /**
     * 中序遍历
     */
    public void postOrder() {
        postOrder(this.root);
    }

    private void postOrder(Node root) {
        if (root == null) {
            return;
        }
        postOrder(root.left);
        postOrder(root.right);
        System.out.print(root.value + ",");
    }

    public void levelOrder() {
        if (this.root == null) {
            return;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.offer(this.root);
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            System.out.println(cur.value);

            if (null != cur.left) {
                queue.offer(cur.left);
            }
            if (null != cur.right) {
                queue.offer(cur.right);
            }
        }
    }

    public T minimum() {
        if (size == 0) {
            return null;
        }
        Node cur = minimum(this.root);
        return cur.value;
    }

    private Node minimum(Node root) {
        if (root.left == null) {
            return root;
        }
        return minimum(root.left);
    }

    public T maximum() {
        if (size == 0) {
            return null;
        }
        Node cur = maximum(this.root);
        return cur.value;
    }

    private Node maximum(Node root) {
        if (root.right == null) {
            return root;
        }
        return maximum(root.right);
    }

    public T removeMin() {
        T min = minimum();
        this.root = removeMin(this.root);
        return min;
    }

    private Node removeMin(Node root) {
        if (root.left == null) {
            Node right = root.right;
            root.right = null;
            size--;
            return right;
        }
        root.left = removeMin(root.left);
        return root;
    }

    public T removeMax() {
        T max = maximum();
        this.root = removeMax(this.root);
        return max;
    }

    private Node removeMax(Node root) {
        if (root.right == null) {
            Node left = root.left;
            root.left = null;
            size--;
            return left;
        }

        root.right = removeMax(root.right);
        return root;
    }

    public void remove(T ele) {
        this.root = remove(this.root, ele);
    }

    public Node remove(Node root, T ele) {
        if (root.value.equals(ele)) {
            size--;
            Node left = root.left;
            Node right = root.right;

            //如果他的左子树为空的话，直接将该节点删除后返回他的右子树就可以了
            if (left == null) {
                root.right = null;
                size--;
                return right;
            }

            if (right == null) {
                root.left = null;
                size--;
                return left;
            }

            //找到删除节点之后的最小节点，并将该节点替换删除节点的位置
            Node minNode = minimum(root.right);
            minNode.right = removeMin(root.right);
            minNode.left = root.left;
            root.right = null;
            root.left = null;

            return minNode;
        } else if (root.value.compareTo(ele) > 0) {
            root.left = remove(root.left, ele);
        } else {
            root.right = remove(root.right, ele);
        }
        return root;
    }
}
