package com.java.test.leetcode;

/**
 * @author pengyongjian
 * @Description:
 * @date 2020-09-14 09:27
 */
public class LRUCache {

    class Node {
        private int key;
        private int value;
        private Node next;
        private Node pref;

        public Node(int key, int value, Node pref, Node next) {
            this.key = key;
            this.value = value;
            this.pref = pref;
            this.next = next;
        }

        public String toString(){
            return "{" + key + ":" + value + "," +
                    (pref == null ? "null" : pref.key) + "," +
                    (next == null ? "null" : next.key) + "}";
        }
    }

    private Node node;

    private int length;

    private int num;

    public LRUCache(int length) {
        this.length = length;
        this.num = 0;
    }

    public int get(int key) {
        Node node = getNode(key);
        System.out.println(this + "\t" + key + node);
        if (node != null) {
            return node.value;
        }
        return -1;
    }

    // 获取节点，并把节点放到最前面
    private Node getNode(int key) {
        Node node = this.node;
        while (node != null) {
            if (node.key == key) {
                return revertNode(node);
            }
            node = node.next;
        }
        return null;
    }

    // 把node提到首节点
    private synchronized Node revertNode(Node node) {
        if(node == this.node){
            return this.node;
        }
        // 父节点变更
        if(node.pref != null){
            node.pref.next = node.next;
        }
        // 子节点变更
        if(node.next != null){
            node.next.pref = node.pref;
        }
        // 头节点变更
        node.next = this.node;
        this.node.pref = node;
        this.node = node;
        node.pref = null;
        return this.node;
    }

    public void put(int key, int value) {
        Node node = getNode(key);
        if (node == null) {
            this.node = new Node(key, value, null, this.node);
            if(this.node.next != null){
                this.node.next.pref = this.node;
            }
            if (num < length) {
                num++;
            } else {
                del();
            }
        } else {
            node.value = value;
        }
        System.out.println(this);
    }

    private void del() {
        Node node = this.node;
        while (node != null){
            if(node.next == null){
                node.pref.next = null;
                break;
            }
            node = node.next;
        }
    }

    public String toString(){
        StringBuilder str = new StringBuilder();
        Node node = this.node;
        while (node != null){
            str.append(node.toString());
            node = node.next;
        }
        String x = str.toString();
        return x;
    }

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);
        cache.put(2, 1);
        cache.put(3, 2);
        cache.get(3);
        cache.get(2);
        cache.put(4, 3);
        cache.get(3);
        cache.get(4);
    }
}