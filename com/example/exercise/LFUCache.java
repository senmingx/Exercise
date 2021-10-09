package com.example.exercise;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

// LC 460
public class LFUCache {

    // Key: key, value: list node contains K,V pair
    private Map<Integer, ListNode> keyToNode;

    // Key: frequency, value: list of nodes with same frequency
    // DoublyLinkedList: head is most recently touched, tail is least recently touched
    private Map<Integer, DoublyLinkedList> freqToList;

    private final int capacity;

    // Min frequency in cache
    private int minFreq;

    // Initializes the object with the capacity of the data structure.
    public LFUCache(int capacity) {
        keyToNode = new HashMap<>();
        freqToList = new HashMap<>();
        this.capacity = capacity;
    }

    // Gets the value of the key if the key exists in the cache. Otherwise, returns -1.
    //
    // Time: O(1)
    public int get(int key) {
        // Corner case: capacity == 0
        if (capacity == 0) {
            return -1;
        }

        // 1. Check if key exist in keyToNode, if not, returns -1
        // 2. If yes, increase frequency of node by 1
        ListNode node = keyToNode.get(key);
        if (node == null) {
            return -1;
        }

        incrementFreq(node);
        return node.value;
    }

    // Update the value of the key if present, or inserts the key if not already present. When the cache reaches its
    // capacity, it should invalidate and remove the least frequently used key before inserting a new item.
    // For this problem, when there is a tie (i.e., two or more keys with the same frequency), the least recently used
    // key would be invalidated.
    //
    // Time: O(1)
    public void put(int key, int value) {
        // Corner case: capacity == 0
        if (capacity == 0) {
            return;
        }

        // Case 1: Key exists, update its value, increment its node's frequency by 1
        // Case 2: Key does not exist, create list node <K,V,1>
        //         If cache is full, remove tail from list with min frequency and keyToNode
        //         Add new node to list with freq=1, add <K,node> to keyToNode
        ListNode node = keyToNode.get(key);
        if (node != null) { // Case 1
            node.value = value;
            incrementFreq(node);
        } else { // Case 2
            node = new ListNode(key, value);
            if (keyToNode.size() == capacity) {
                ListNode lastNode = freqToList.get(minFreq).last();
                keyToNode.remove(lastNode.key);
                removeFromList(lastNode);
            }
            minFreq = 1;
            addToList(node);
            keyToNode.put(key, node);
        }
    }

    // Increment the frequency of list node
    private void incrementFreq(ListNode node) {
        // 1. Delete node from freq list
        // 2. Increase node.freq by 1
        // 3. Move node to head of new freq list
        removeFromList(node);
        if (node.freq == minFreq && !freqToList.containsKey(node.freq)) {
            minFreq++;
        }
        node.freq++;
        addToList(node);
    }

    // Add node to head of list with frequency = node.freq
    private void addToList(ListNode node) {
        DoublyLinkedList list = freqToList.get(node.freq);
        if (list == null) {
            list = new DoublyLinkedList();
            freqToList.put(node.freq, list);
        }
        list.addToHead(node);
    }

    // Remove node from list with frequency = node.freq
    private void removeFromList(ListNode node) {
        DoublyLinkedList list = freqToList.get(node.freq);
        list.remove(node);
        if (list.isEmpty()) {
            freqToList.remove(node.freq);
        }
    }

    private class ListNode {
        int key;
        int value;
        int freq;
        ListNode next;
        ListNode prev;

        public ListNode() {
            this(-1, -1);
        }

        public ListNode(int k, int v) {
            key = k;
            value = v;
            freq = 1;
        }
    }

    private class DoublyLinkedList {
        private ListNode head;
        private ListNode tail;
        private int size;

        public DoublyLinkedList() {
            head = new ListNode();
            tail = new ListNode();
            head.next = tail;
            tail.prev = head;
            size = 0;
        }

        public void remove(ListNode node) {
            ListNode prev = node.prev;
            ListNode next = node.next;
            prev.next = next;
            next.prev = prev;
            size--;
        }

        public void addToHead(ListNode node) {
            ListNode next = head.next;
            head.next = node;
            node.prev = head;
            node.next = next;
            next.prev = node;
            size++;
        }

        public ListNode last() {
            return tail.prev;
        }

        private boolean isEmpty() {
            return size == 0;
        }
    }

    public static void main(String[] args) {
        testWithNormalOperations();
        testWithNoCapacity();
        testWithCapacityOne();
    }

    private static void testWithCapacityOne() {
        LFUCache cache = new LFUCache(1);
        cache.put(2,1);
        assertEquals(1, cache.get(2));
        cache.put(3,2);
        assertEquals(-1, cache.get(2));
        assertEquals(2, cache.get(3));
    }

    private static void testWithNoCapacity() {
        LFUCache cache = new LFUCache(0);
        cache.put(1,1); // Cache: []
        assertEquals(-1, cache.get(1)); // Cache: []
    }

    private static void testWithNormalOperations() {
        LFUCache cache = new LFUCache(3);
        assertEquals(-1, cache.get(1)); // Cache: []
        cache.put(1,1); // Cache: [(1,1,1)]
        cache.put(2,2); // Cache: [(1,1,1), (2,2,1)]
        cache.put(3,3); // Cache: [(1,1,1), (2,2,1), (3,3,1)]
        assertEquals(1, cache.get(1)); // Cache: [(1,1,2), (2,2,1), (3,3,1)]
        cache.put(4,4); // Cache: [(1,1,2), (4,4,1), (3,3,1)]
        assertEquals(4, cache.get(4)); // Cache: [(1,1,2), (4,4,2), (3,3,1)]
        assertEquals(-1, cache.get(2)); // Cache: [(1,1,2), (4,4,2), (3,3,1)]
        assertEquals(1, cache.get(1)); // Cache: [(1,1,3), (4,4,2), (3,3,1)]
        assertEquals(3, cache.get(3)); // Cache: [(1,1,2), (4,4,2), (3,3,2)]
        assertEquals(-1, cache.get(10)); // Cache: [(1,1,2), (4,4,2), (3,3,1)]
        cache.put(5,5); // Cache: [(1,1,2), (4,4,2), (3,3,1)]
    }
}
