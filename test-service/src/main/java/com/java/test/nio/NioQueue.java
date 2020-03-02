package com.java.test.nio;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class NioQueue {
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    /** size */
    private int size;
    /** 最早留言 */
    private int start;
    /** 最新留言 */
    private int end;

    /** 留言记录 */
    private Node[] nodes;
    private Map<String, Integer> userIndexMap;

    /**
     *
     * @param size 1-1000
     */
    public NioQueue(int size) throws Exception {
        if (size < 1 || size > 1000) throw new  Exception();
        this.size = size;
        start = 0;
        end = 0;
        nodes = new Node[size];
        nodes[0] = new Node(0, "/127.0.0.1:0000", format.format(new Date()), "begin");
        System.out.println(Node.toString(nodes[0]));
        userIndexMap = new ConcurrentHashMap<>();
    }

    public List<Node> getNodes(String user){
        // 获取上次发送消息位置
        Integer index = userIndexMap.get(user);
        int begin = start;
        if (index != null) begin = index;
        if (begin == end) {
            return null;
        }
        // 获取未曾发送的消息
        begin = begin == size - 1 ? 0 : begin + 1;
        List<Node> msgs = new LinkedList<>();
        if (begin <= end) {
            for (int i = begin; i <= end; i++) {
                msgs.add(nodes[i]);
            }
        } else {
            for (int i = begin; i < size; i++) {
                msgs.add(nodes[i]);
            }
            for (int i = 0; i <= end; i++) {
                msgs.add(nodes[i]);
            }
        }
        userIndexMap.put(user, end);
        return msgs;
    }

    public Node setNode(String user, String msg){
        if (user == null || msg == null || "".equals(user) || "".equals(msg)){
            return null;
        }
        if (size == (start + end)) {
            if (start == (size - 1)) {
                start = 0;
            } else {
                start++;
            }
            if (end == (size - 1)) {
                end = 0;
            } else {
                end++;
            }
        } else {
            end++;
        }
        nodes[end] = new Node(end, user, format.format(new Date()),msg);
        return nodes[end];
    }
}
class Node{
    private int index;
    private String user;
    private String date;
    private String msg;

    public Node(int index, String user, String date, String msg) {
        this.index = index;
        this.user = user;
        this.date = date;
        this.msg = msg;
    }

    public int getIndex() {
        return index;
    }
    public void setIndex(int index) {
        this.index = index;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static String toString(Node node) {
        if (node == null) {
            return "";
        }
        return node.getUser() + "\t" + node.getDate() + '\n'+ node.getMsg();
    }

    public static String toString(List<Node> nodes) {
        if (nodes == null) {
            return null;
        }
        StringBuilder stringBuilder = new StringBuilder();
        for (Node node : nodes) {
            if (node == null) {
                continue;
            }
            stringBuilder.append("\n").append(node.getUser())
                    .append("\t").append(node.getDate())
                    .append("\n").append(node.getMsg());
        }
        return stringBuilder.toString();
    }
}