package com.example.exercise;

public class InsertToSortedCircularLinkedList {
    public Node insert(Node head, int insertVal) {
        Node node = new Node(insertVal);
        // Corner case: Empty list
        if (head == null) {
            node.next = node;
            return node;
        }

        // Find the tail of list (greatest value), when prev.val > cur.val, then prev will be max, curr will be min
        //
        // Case 1: insertVal within list (min <= insertVal <= max)
        // Case 2: insertVal out of list (insertVal < min or insertVal > max)
        // Case 3: Uniform list

        Node prev = head, curr = head.next;
        boolean isInsert = false;

        while (true) {
            if (prev.val <= insertVal && insertVal <= curr.val) { // Case 1
                isInsert = true;
            } else if (prev.val > curr.val) { // Case 2
                if (insertVal > prev.val || insertVal < curr.val)  {
                    isInsert = true;
                }
            }

            if (isInsert) {
                prev.next = node;
                node.next = curr;
                return head;
            }

            prev = curr;
            curr = curr.next;

            // Finish one round
            if (prev == head) {
                break;
            }
        }

        // Case 3
        node.next = head.next;
        head.next = node;

        return head;
    }

    private class Node {
        public int val;
        public Node next;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _next) {
            val = _val;
            next = _next;
        }
    }
}
