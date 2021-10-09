package com.example.exercise;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {

    private ListNode head; // Most recently used
    private ListNode tail; // Least recently used

    // Key: key, value: list node contains key
    private Map<Integer, ListNode> map;

    private final int capacity; // Capacity of cache

    // Initialize the LRU cache with positive size capacity.
    public LRUCache(int capacity) {
        head = new ListNode();
        tail = new ListNode();
        head.next = tail;
        tail.prev = head;

        map = new HashMap<>();
        this.capacity = capacity;
    }

    // Return the value of the key if the key exists, otherwise return -1.
    public int get(int key) {
        // 1. Check if map contains key, if not, return -1
        // 2. If yes, then move list node to head of list, then return its value

        ListNode node = map.get(key);
        if (node == null) {
            return -1;
        }

        moveToHead(node);
        return node.val;
    }

    // Update the value of the key if the key exists. Otherwise, add the key-value pair to the cache.
    // If the number of keys exceeds the capacity from this operation, evict the least recently used key.
    public void put(int key, int value) {
        // Case 1: key exist, move list node to list head, then update its value
        // Case 2: key does not exist, create list node with <K,V>
        //      2.1: cache is not full, add <K,node> pair to map, also add node to head of list
        //      2.2: cache is full, remove the node at tail, remove it from map as well, then add new pair to map,
        //           add new node to head of list

        ListNode node = map.get(key);
        if (node != null) { // Case 1
            node.val = value;
            moveToHead(node);
        } else { // Case 2
            node = new ListNode(key, value);
            if (map.size() == capacity) {
                ListNode toRemove = remove(tail.prev);
                map.remove(toRemove.key);
            }

            map.put(key, node);
            addToHead(node);
        }
    }

    private ListNode remove(ListNode node) {
        ListNode prev = node.prev;
        ListNode next = node.next;
        prev.next = next;
        next.prev = prev;
        node.prev = null;
        node.next = null;
        return node;
    }

    private void addToHead(ListNode node) {
        ListNode next = head.next;
        // Insert node between head and next
        head.next = node;
        node.prev = head;
        node.next = next;
        next.prev = node;
    }

    private void moveToHead(ListNode node) {
        // Move a list node to head of list equals to remove it from list then add back to head of list
        remove(node);
        addToHead(node);
    }

    private class ListNode {
        ListNode prev;
        ListNode next;
        int key;
        int val;

        public ListNode() {
            this(-1, -1);
        }

        public ListNode(int k, int v) {
            key = k;
            val = v;
        }
    }

    public static void main(String[] args) {
        LRUCache cache = new LRUCache(2);
        cache.put(1, 1);
        cache.put(2, 2);
        cache.put(3, 3);
        System.out.println(cache.get(1));
        cache.put(4, 4);
        System.out.println(cache.get(1));
        System.out.println(cache.get(2));
        System.out.println(cache.get(3));
        System.out.println(cache.get(4));
    }
}
