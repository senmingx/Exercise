package com.example.exercise;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MinDeletionCostToAvoidDupliacate {

    public int minCost(String s, int[] cost) {
        // Corner cases
        if (s == null) {
            throw new IllegalArgumentException();
        }
        if (s.isEmpty()) {
            return 0;
        }

        // For repeating chars, only keep the one with max cost
        int minCost = 0;

        int sum = cost[0]; // sum of repeating chars' cost
        int max = cost[0]; // min cost of repeating chars
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == s.charAt(i-1)) {
                sum += cost[i];
                max = Math.max(max, cost[i]);
            } else {
                minCost += sum - max;
                sum = cost[i];
                max = cost[i];
            }
        }

        // Post-processing case: last char in repeating char
        minCost += sum - max;

        return minCost;
    }

    public static void main(String[] args) {
        MinDeletionCostToAvoidDupliacate sol = new MinDeletionCostToAvoidDupliacate();

        assertEquals(3, sol.minCost("abaac", new int[] {1,2,3,4,5}));
        assertEquals(0, sol.minCost("abc", new int[] {1,2,3}));
        assertEquals(2, sol.minCost("aabaa", new int[] {1,2,3,4,1}));
    }
}
