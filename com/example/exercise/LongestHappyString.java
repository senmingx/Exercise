package com.example.exercise;

import javafx.util.Pair;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class LongestHappyString {
    // Heap size is 3, which is O(1)
    // Time: O(a + b + c)
    // Space: O(1)
    public String longestDiverseString(int a, int b, int c) {
        if (a < 0 || b < 0 || c < 0) {
            throw new IllegalArgumentException();
        }

        // We want to use the largest count chars first
        // 1. Each round the largest 2 letters, first and second
        // 2. Output one second, as many first as possible

        // aab | aab | ab | ac

        StringBuilder res = new StringBuilder();

        // Pair: key is num of remaining, value is char
        // maxHeap with the largest count of character at top
        Queue<Pair<Integer, Character>> maxHeap = new PriorityQueue<>(new Comparator<Pair<Integer, Character>>() {
            @Override
            public int compare(Pair<Integer, Character> o1, Pair<Integer, Character> o2) {
                return o2.getKey() - o1.getKey();
            }
        });

        if (a > 0) maxHeap.offer(new Pair<>(a, 'a'));
        if (b > 0) maxHeap.offer(new Pair<>(b, 'b'));
        if (c > 0) maxHeap.offer(new Pair<>(c, 'c'));

        while (!maxHeap.isEmpty()) {
            // Case: when only one letter left
            if (maxHeap.size() == 1) {
                // Can at most add 2 same letters
                Pair<Integer, Character> last = maxHeap.poll();
                int num = Math.min(last.getKey(), 2);
                for (int i = 0; i < num; i++) {
                    res.append(last.getValue());
                }
                break;
            }

            Pair<Integer, Character> first = maxHeap.poll();
            Pair<Integer, Character> second = maxHeap.poll();

            int firstCount = first.getKey();
            int secondCount = second.getKey();
            char firstLetter = first.getValue();
            char secondLetter = second.getValue();

            // If first count == second count, then add first once, second once
            // Otherwise first count > second count, then add first twice, second once
            //
            // Because each round we only append second once, so second can be use as separator
            int num = firstCount > secondCount ? 2 : 1;
            for (int i = 0; i < num; i++) {
                res.append(firstLetter);
                firstCount--;
            }
            res.append(secondLetter);
            secondCount--;

            // Update Letter's count in maxHeap
            if (firstCount > 0) {
                maxHeap.offer(new Pair<>(firstCount, firstLetter));
            }
            if (secondCount > 0) {
                maxHeap.offer(new Pair<>(secondCount, secondLetter));
            }
        }

        return res.toString();
    }

    public static void main(String[] args) {
        LongestHappyString sol = new LongestHappyString();

        for (int a = 0; a <= 100; a++) {
            for (int b = 0; b <= 100; b++) {
                for (int c = 0; c <= 100; c++) {
                    String res = sol.longestDiverseString(a, b, c);
                    System.out.println("(" + a + "," + b + "," + c + "): " + res);
                    // Test res does not contain any of aaa, bbb, ccc
                    assertFalse(res.indexOf("aaa") >= 0
                            || res.indexOf("bbb") >= 0
                            || res.indexOf("ccc") >= 0);
                }
            }
        }

    }
}
