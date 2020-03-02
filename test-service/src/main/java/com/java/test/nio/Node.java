package com.java.test.nio;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class Node{
    private static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private int index;
    private String date;
    private String user;
    private String msg;

    public Node(int index, String user, String msg) {
        this.index = index;
        this.user = user;
        this.msg = msg;
        this.date = format.format(new Date());
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

    @Override
    public String toString() {
        return "Node{" +
                "index=" + index +
                ", date='" + date + '\'' +
                ", user='" + user + '\'' +
                ", msg='" + msg + '\'' +
                '}';
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
            stringBuilder.append("\n").append(node.getUser()).append("\t").append(node.getDate()).append("\n").append(node.getMsg());
        }
        return stringBuilder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node node = (Node) o;
        return index == node.index &&
                Objects.equals(date, node.date) &&
                Objects.equals(user, node.user) &&
                Objects.equals(msg, node.msg);
    }

    @Override
    public int hashCode() {
        return Objects.hash(index, date, user, msg);
    }
}