package com.example.exercise;
import java.util.*;

public class PopulateNextRightPointerInNode {

    // BFS
    // Time: O(n)
    // Space: O(n)
    public Node connect(Node root) {
        // Corner case
        if (root == null) {
            return root;
        }

        // Level order traversal by BFS
        // Each time prev.next = cur

        Node prev = null;
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            // Reset prev to null at the beginning of each level
            prev = null;

            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node cur = queue.poll();
                if (prev != null) {
                    prev.next = cur;
                }
                prev = cur;

                if (cur.left != null) {
                    queue.offer(cur.left);
                }
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
            }
        }

        return root;
    }

    // Use constant space
    // Time: O(n)
    // Space: O(1)
    public Node connect2(Node root) {
        // Corner case
        if (root == null) {
            return root;
        }

        // We have next pointer for each node, so we can keep each level as a linked list,
        // so we can get rid of queue for level order traversal
        //
        // When processing level N, we need to do it from level N-1

        Node head = root;
        while (head != null) {
            Node nextHead = null;
            Node cur = head;
            Node nextLevelPrev = null;

            while (cur != null) {
                // Process cur.left
                if (cur.left != null) {
                    if (nextHead == null) {
                        nextHead = cur.left;
                    }
                    if (nextLevelPrev != null) {
                        nextLevelPrev.next = cur.left;
                    }
                    nextLevelPrev = cur.left;
                }
                // Process cur.right
                if (cur.right != null) {
                    if (nextHead == null) {
                        nextHead = cur.right;
                    }
                    if (nextLevelPrev != null) {
                        nextLevelPrev.next = cur.right;
                    }
                    nextLevelPrev = cur.right;
                }
                // Move to cur.next
                cur = cur.next;
            }

            // Move to next level
            head = nextHead;
        }

        return root;
    }

    private class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }
}
