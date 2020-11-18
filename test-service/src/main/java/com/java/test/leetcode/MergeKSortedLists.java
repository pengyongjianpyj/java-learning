package com.java.test.leetcode;


/**
 * @author pengyongjian
 * @Description:
 * @date 2020-11-02 15:20
 */
public class MergeKSortedLists {

    public static void main(String[] args) {

    }

    static class Solution {
        public ListNode mergeKLists(ListNode[] lists) {
            if(lists == null || lists.length == 0){
                return null;
            }
            if(lists.length == 1){
                return lists[0];
            }
            // 排序
            for (int i = 0; i < lists.length - 1; i++) {
                sortListNodeArrayFirst(lists, 0, false);
            }
            // 结果
            ListNode result = new ListNode(0);
            ListNode last = result;
            while (lists[0] != null) {
                last.next = lists[0];
                last = lists[0];
                lists[0] = lists[0].next;
                sortListNodeArrayFirst(lists, 0, true);
            }

            return result.next;
        }

        private ListNode[] sortListNodeArrayFirst(ListNode[] lists, int begin, boolean isBreak){
            ListNode tmp = null;
            for (int i = begin; i < lists.length - 1; i++) {
                if(lists[i+1] != null){
                    if(lists[i] != null){
                        if(lists[i].val > lists[i + 1].val){
                            tmp = lists[i];
                            lists[i] = lists[i + 1];
                            lists[i + 1] = tmp;
                        } else {
                            if(isBreak){
                                break;
                            }
                        }
                    } else {
                        lists[i] = lists[i + 1];
                        lists[i + 1] = null;
                    }
                }
            }
            return lists;
        }
    }
}

class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
