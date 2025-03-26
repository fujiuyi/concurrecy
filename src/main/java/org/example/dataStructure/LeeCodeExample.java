package org.example.dataStructure;

import java.util.*;
import java.util.stream.Collectors;

class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

public class LeeCodeExample {
    public ListNode removeElements(ListNode head, int val) {

        if (null == head) {
            return null;
        }
        ListNode res = removeElements(head.next, val);
        if (head.val == val) {
            return res;
        } else {
            head.next = res;
            return head;
        }


    }

    public ListNode removeElements(ListNode head, ListNode next, int val) {
        if (next == null) {
            return head;
        }
        if (next.val == val) {
            head.next = next.next;
        } else {
            head = head.next;
        }
        return removeElements(head, head.next, val);

    }

    public ListNode reverseList(ListNode head) {

        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;

    }

    public static int iceBreakingGame(int num, int target) {

        int len = num;
        int[] arr = new int[len];
        for (int i = 0; i < num; i++) {
            arr[i] = i;
        }
        int index = 0;
        while (len > 1) {
            int count = 0;
            while (count < target) {
                if (arr[index] != -1) {
                    count++; // 只有未出局的人才计数
                }
                if (count < target) { // 如果还没找到第 k 个，继续移动
                    index = (index + 1) % num;
                }
            }
            arr[index] = -1;
            len--;
        }

        for (int i = 0; i < num; i++) {
            if (arr[i] != -1)
                return i;
        }
        return -1;
    }

    public static int josephus(int n, int k) {

        if (n == 1) {
            return 0;
        }

        int x = josephus(n - 1, k);

        return (x + k) % n;
    }

    public static boolean isPalindrome(ListNode head) {

        Stack<Integer> stack = new Stack<>();
        ListNode cur = head;
        while (cur != null) {
            stack.push(cur.val);
            cur = cur.next;
        }

        while (head != null) {
            if (head.val != stack.pop()) {
                return false;
            }
            head = head.next;
        }
        return true;
    }

    public int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }
        Queue<TreeNode> queue = new LinkedList<>();

        int ans = 0;
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size > 0) {
                TreeNode cur = queue.poll();
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
                if (cur.left != null) {
                    queue.offer(cur.left);
                }
                size--;
            }
            ans++;
        }
        return ans;

    }

    public static int getMinimumDifference(TreeNode root) {
        List<Integer> list = getResult(root);
        int min = Integer.MAX_VALUE;
        for (int i = 1; i < list.size(); i++) {
            int temp = Math.abs(list.get(i) - list.get(i - 1));
            if (temp < min) {
                min = temp;
            }
        }
        return min;
    }

    private static List<Integer> getResult(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        inOrderTreeNode(root, list);
        return list;
    }

    private static void inOrderTreeNode(TreeNode root, List<Integer> list) {
        if (root == null) {
            return;
        }
        inOrderTreeNode(root.left, list);
        list.add(root.val);
        inOrderTreeNode(root.right, list);
    }

    public int diameterOfBinaryTree(TreeNode root) {
        return 0;
    }

    private static int ans = 0;

    private static int getDeepMax(TreeNode root) {
        if (root == null) {
            return -1;
        }
        int l = getDeepMax(root.left) + 1;
        int r = getDeepMax(root.right) + 1;
        ans = Math.max(ans, l + r);
        return Math.max(l, r);
    }

    public int uniqueMorseRepresentations(String[] words) {

        String[] arr = new String[]{".-", "-...", "-.-.", "-..", ".", "..-.", "--.", "....", "..", ".---", "-.-",
                ".-..", "--", "-.", "---", ".--.", "--.-", ".-.", "...", "-", "..-", "...-", ".--", "-..-", "-.--", "--.."};
        Set<String> set = new HashSet<>();

        for (int i = 0; i < words.length; i++) {
            StringBuilder sb = new StringBuilder();
            String cur = words[i];
            for (int j = 0; j < words[i].length(); j++) {
                sb.append(arr[cur.charAt(j) - 'a']);
            }
            set.add(sb.toString());
        }
        return set.size();

    }

    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            int j = map.getOrDefault(num, 0);
            map.put(num, ++j);
        }

        Queue<Integer> queue = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return -map.get(o1).compareTo(map.get(o2));
            }
        });

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (queue.size() < k) {
                queue.offer(entry.getKey());
            } else {
                if (map.get(queue.peek()) < entry.getValue()) {
                    queue.poll();
                    queue.offer(entry.getKey());
                }
            }
        }

        int[] arr = new int[queue.size()];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = queue.poll();
        }

        return arr;

    }

    public int kthSmallest(int[][] matrix, int k) {
        Queue<Integer> queue = new PriorityQueue<>(Comparator.reverseOrder());

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                if (i * matrix.length + j < k) {
                    queue.offer(matrix[i][j]);
                } else {
                    if (!queue.isEmpty() && queue.peek() > matrix[i][j]) {
                        queue.poll();
                        queue.offer(matrix[i][j]);
                    }
                }
            }
        }
        return queue.poll();
    }

    public static void main(String[] args) {
        TreeNode node = new TreeNode(236);
        node.left = new TreeNode(104);
        node.right = new TreeNode(701);

        System.out.println(Arrays.toString(new LeeCodeExample().topKFrequent(new int[]{1, 1, 1, 2, 2, 3}, 2)));

    }
}

class NumArray {

    private SegmentTree<Integer> segmentTree;

    public NumArray(int[] nums) {
        segmentTree = new SegmentTree<>(Arrays.stream(nums).boxed().toArray(Integer[]::new), Integer::sum);
    }

    public void update(int index, int val) {
        segmentTree.set(index, val);
    }

    public int sumRange(int left, int right) {
        return segmentTree.query(left, right);
    }
}

class WordDictionary {
    private class Node {
        public boolean isEnd;
        public Map<Character, Node> next;

        public Node(boolean isEnd) {
            this.isEnd = isEnd;
            next = new HashMap<>();
        }

        public Node() {
            this(false);
        }
    }

    private Node node;

    public WordDictionary() {
        node = new Node();
    }

    public void addWord(String word) {
        Node cur = this.node;
        for (int i = 0; i < word.length(); i++) {
            Character c = word.charAt(i);
            if (!cur.next.containsKey(c)) {
                cur.next.put(c, new Node());
            }
            cur = cur.next.get(c);
        }
        cur.isEnd = true;
    }

    public boolean search(String word) {
        return search(this.node, word, 0);
    }

    private boolean search(Node node, String word, int index) {

        if (index == word.length()) {
            return node.isEnd;
        }

        Character c = word.charAt(index);
        if (c.equals('.')) {
            for (Node node1 : node.next.values()) {
                if (search(node1, word, index + 1)) {
                    return true;
                }
            }
            return false;
        } else {
            if (node.next.containsKey(c)) {
                return search(node.next.get(c), word, index + 1);
            } else {
                return false;
            }
        }
    }
}

class MapSum {
    private class Node {

        public boolean isEnd;
        public int value = 0;
        public Map<Character, Node> next;

        public Node(boolean isEnd) {
            this.isEnd = isEnd;
            next = new HashMap<>();
        }

        public Node() {
            this(false);
        }
    }

    private Node node;

    public MapSum() {
        node = new Node();
    }

    public void insert(String key, int val) {

        Node cur = this.node;
        for (int i = 0; i < key.length(); i++) {
            Character c = key.charAt(i);
            if (!cur.next.containsKey(c)) {
                cur.next.put(c, new Node());
            }
            cur = cur.next.get(c);
        }
        cur.isEnd = true;
        cur.value = val;
    }

    public int sum(String prefix) {

        Node cur = this.node;
        for (int i = 0; i < prefix.length(); i++) {
            Character c = prefix.charAt(i);
            if (!cur.next.containsKey(c)) {
                return 0;
            }
            cur = cur.next.get(c);
        }

        return sum(cur);
    }

    private int sum(Node node) {
        int res = node.value;
        for (Node node1 : node.next.values()) {
            res += sum(node1);
        }
        return res;
    }

}