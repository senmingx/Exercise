package com.example.exercise;

public class ReverseNodesInKGroup {

    public ListNode reverseKGroup(ListNode head, int k) {
        // Base case: length < k
        if (length(head) < k) {
            return head;
        }

        // 1. Reverse first k nodes
        // 2. Reverse from k+1 to end of list
        // 3. Connect two parts

        ListNode kth = getKth(head, k);
        ListNode nextHead = kth.next;
        kth.next = null;

        reverse(head);
        // After reverse, head will be last, and kth will be first

        nextHead = reverseKGroup(nextHead, k);

        head.next = nextHead;
        return kth;
    }

    private ListNode reverse(ListNode head) {
        // Base case: Last node
        if (head.next == null) {
            return head;
        }

        ListNode newHead = reverse(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }

    // k is 1-based
    private ListNode getKth(ListNode head, int k) {
        ListNode cur = head;
        for (int i = 1; i < k; i++) {
            cur = cur.next;
        }
        return cur;
    }

    private int length(ListNode head) {
        int length = 0;
        while (head != null) {
            length++;
            head = head.next;
        }
        return length;
    }

    private class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}


