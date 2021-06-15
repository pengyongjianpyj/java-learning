package com.java.aaa;

/**
 * @author pengyongjian
 * @Description:
 * @date 2021/6/10 5:14 下午
 */
public class BTest {
  public static void main(String[] args) {
    ListNode listNode4 = new ListNode(4, null);
    ListNode listNode3 = new ListNode(3, listNode4);
    ListNode listNode2 = new ListNode(2, listNode3);
    ListNode listNode = new ListNode(1, listNode2);
    ListNode reverseList = reverseList2(listNode);
//    ListNode reverseList = reverseList(listNode);
    System.out.println(reverseList);
  }

  static class ListNode{
    int val;
    ListNode next;

    public ListNode(int val, ListNode next) {
      this.val = val;
      this.next = next;
    }
  }

  public static ListNode reverseList(ListNode head) {
    if (head == null || head.next == null) {
        return head;
    }
    ListNode newHead = reverseList(head.next);
    head.next.next = head;
    head.next = null;
    return newHead;
  }

  public static ListNode reverseList2(ListNode head) {
    ListNode pre = null;
    ListNode tmp = head;
    while (tmp != null) {
      ListNode next = tmp.next;
      tmp.next = pre;
      pre = tmp;
      tmp = next;
    }
    return pre;
  }
}
