package com.java.test.nio;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Node{
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