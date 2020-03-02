package com.java.test.nio;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class NioQueue {
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
        nodes[0] = new Node(0, "/127.0.0.1:0000", "begin");
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
        nodes[end] = new Node(end, user,msg);
        return nodes[end];
    }
}
