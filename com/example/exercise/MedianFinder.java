package com.example.exercise;

import java.util.*;

public class MedianFinder {

    private Queue<Integer> minHeap; // Larger half of data stream
    private Queue<Integer> maxHeap; // Smaller half of data stream

    // Ensure maxHeap.size == minHeap.size + 0 or 1

    public MedianFinder() {
        minHeap = new PriorityQueue<>((num1, num2) -> num1 - num2);
        maxHeap = new PriorityQueue<>((num1, num2) -> num2 - num1);
    }

    public void addNum(int num) {
        if (maxHeap.isEmpty()) {
            maxHeap.offer(num);
        } else {
            if (num <= maxHeap.peek()) {
                maxHeap.offer(num);
            } else {
                minHeap.offer(num);
            }
            transfer();
        }
    }

    public double findMedian() {
        // Corner case: no number exists

        int size = minHeap.size() + maxHeap.size();
        if (size % 2 != 0) {
            return maxHeap.peek();
        } else {
            return (minHeap.peek() + maxHeap.peek()) / 2.0;
        }
    }

    private void transfer() {
        // When maxHeap.size > minHeap.size + 1
        while (maxHeap.size() > minHeap.size() + 1) {
            minHeap.offer(maxHeap.poll());
        }
        // When maxHeap.size < minHeap.size
        while (maxHeap.size() < minHeap.size()) {
            maxHeap.offer(minHeap.poll());
        }
    }
}
