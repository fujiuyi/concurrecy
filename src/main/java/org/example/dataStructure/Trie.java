package org.example.dataStructure;

import java.util.HashMap;
import java.util.Map;

public class Trie {

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
    private int size;

    public Trie() {
        this.node = new Node();
        this.size = 0;
    }

    // 向Trie中添加一个新的单词word
    public void add(String word) {

        Node cur = this.node;
        for (int i = 0; i < word.length(); i++) {
            Character character = word.charAt(i);
            cur.next.computeIfAbsent(character, k -> new Node());
            cur = cur.next.get(character);
        }
        if (!cur.isEnd) {
            cur.isEnd = true;
            size++;
        }
    }

    // 查询单词word是否在Trie中
    public boolean contains(String word) {
        Node cur = this.node;
        for (int i = 0; i < word.length(); i++) {
            Character character = word.charAt(i);
            if (null == cur.next.get(character)) {
                return false;
            }
            cur = cur.next.get(character);
        }
        return cur.isEnd;
    }

    // 查询是否在Trie中有单词以prefix为前缀
    public boolean isPrefix(String prefix) {
        Node cur = this.node;
        for (int i = 0; i < prefix.length(); i++) {
            Character character = prefix.charAt(i);
            if (null == cur.next.get(character)) {
                return false;
            }
            cur = cur.next.get(character);
        }
        return true;
    }

    public void remove(String word) {
        if (word == null || word.isEmpty()) {
            return;
        }
        Node cur = this.node;
        Node lastWordNode = cur;
        Character lastChar = null;
        for (int i = 0; i < word.length(); i++) {
            Character character = word.charAt(i);
            if (null == cur.next.get(character)) {
                return;
            }
            if (cur.isEnd) {
                //找到最后一个可以被复用的位置
                lastWordNode = cur;
                lastChar = character;
            }
            cur = cur.next.get(character);
        }
        if (!cur.isEnd) {
            return;
        }
        if (!cur.next.isEmpty()) {
            cur.isEnd = false;
        } else {
            lastWordNode.next.remove(lastChar);
        }
    }
}
