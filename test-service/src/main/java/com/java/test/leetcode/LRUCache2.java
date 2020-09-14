package com.java.test.leetcode;

/**
 * @author pengyongjian
 * @Description:
 * @date 2020-09-14 09:27
 */
public class LRUCache2 {

    Integer[][] arr;

    public LRUCache2(int num) {
        arr = new Integer[num][2];
    }

    public int get(int key) {
        for (int i = 0; i < arr.length; i++) {
            if ((arr[i][0] != null) && ((int) arr[i][0] == key)) {
                return moveFirst(i);
            }
        }
        return -1;
    }

    public void put(int key, int value) {
        int index = -1;
        for (int i = 0; i < arr.length; i++) {
            if(arr[i][0] == null){
                break;
            }
            if (arr[i][0] == key) {
                index = i;
                break;
            }
        }

        if(index < 0){
            index = arr.length - 1;
        }
        moveFirst(index);
        arr[0][0] = key;
        arr[0][1] = value;
//        System.out.println(this.toString());
    }

    private Integer moveFirst(int index) {
        if(index == 0){
            return arr[0][1];
        }
        Integer key = arr[index][0];
        Integer value = arr[index][1];
        for (int i = index; i > 0; i--) {
            arr[i][0] = arr[i - 1][0];
            arr[i][1] = arr[i - 1][1];
        }
        arr[0][0] = key;
        arr[0][1] = value;
        return arr[0][1];
    }

    public static void main(String[] args) {
        LRUCache2 cache = new LRUCache2(3);
        cache.put(1, 1);
        cache.put(2, 2);
        cache.put(3, 3);
        cache.put(4, 4);
        cache.get(4);
        System.out.println(cache);
        cache.get(3);
        System.out.println(cache);
        cache.get(2);
        System.out.println(cache);
        cache.get(1);
        System.out.println(cache);
        cache.put(5, 5);
        cache.get(1);
        cache.get(2);
        cache.get(3);
        cache.get(4);
        cache.get(5);
    }

    public String toString() {
        String str = "[";
        for (int i = 0; i < arr.length; i++) {
            str += "{" + arr[i][0] + "," + arr[i][1] + "},";
        }
        str += "]";
        return str;
    }
}