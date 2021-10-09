package com.example.exercise;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

public class MergeK {
    /*  m lists, each lists length is n
     *  Method 1: Binary reduction
     *  Time: logm level, each level merge O(mn) -> O(mnlogm)
     *  Space: O(logm)
     */
    public ListNode mergeKLists1(ListNode[] lists) {
        // Corner case check
        if (lists == null) {
            throw new IllegalArgumentException();
        }
        if (lists.length == 0) {
            return null;
        }

        return binaryMerge(lists, 0, lists.length - 1);
    }

    private ListNode binaryMerge(ListNode[] lists, int start, int end) {
        // Base case
        if (start == end) {
            return lists[start];
        }

        int mid = start + (end - start) / 2;
        ListNode left = binaryMerge(lists, start, mid);
        ListNode right = binaryMerge(lists, mid + 1, end);

        return merge(left, right);
    }

    private ListNode merge(ListNode left, ListNode right) {
        // Helper node before actual head, head = headPrev.next
        ListNode headPrev = new ListNode(0);
        ListNode cur = headPrev;

        while (left != null && right != null) {
            if (left.val < right.val) {
                cur.next = left;
                left = left.next;
            } else {
                cur.next = right;
                right = right.next;
            }
            cur = cur.next;
        }
        // Post-processing: left or right not null
        cur.next = left != null ? left : right;

        return headPrev.next;
    }

    /*
     * Method 2: minHeap
     * Time: O(mnlogm), heap size is m, each element has to enter and leave heap once
     * Space: O(m)
     */
    public ListNode mergeKLists2(ListNode[] lists) {
        // Corner case check
        if (lists == null) {
            throw new IllegalArgumentException();
        }
        if (lists.length == 0) {
            return null;
        }

        // The idea is to keep an pointer in each list, each time compare these k pointers and get the min val
        // one to result, then move the pointer to next

        ListNode headPrev = new ListNode();
        ListNode cur = headPrev;

        Queue<ListNode> minHeap = new PriorityQueue<>(new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                return o1.val - o2.val;
            }
        });

        // 1. Add all list heads to minHeap
        // 2. Each time poll from minHeap, add to result, then offer its next to minHeap if it has

        for (ListNode head : lists) {
            if (head != null) {
                minHeap.offer(head);
            }
        }

        while (!minHeap.isEmpty()) {
            ListNode poll = minHeap.poll();
            cur.next = poll;
            cur = cur.next;

            if (poll.next != null) {
                minHeap.offer(poll.next);
            }
        }

        return headPrev.next;
    }

    private class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
     }
}
