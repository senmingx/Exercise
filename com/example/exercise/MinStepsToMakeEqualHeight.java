package com.example.exercise;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MinStepsToMakeEqualHeight {
    public int minSteps(int[] heights) {
        if (heights == null) {
            throw new IllegalArgumentException();
        }
        if (heights.length < 2) {
            return 0;
        }

        // Sort heights first in reverse order
        // E.g.
        // index  0 1 2 3 4 5 6 7 8
        // val    5 5 5 4 4 3 1 1 0
        //
        // 1st:   4 4 4 4 4 3 1 1 0  steps: 3
        // 2nd:   3 3 3 3 3 3 1 1 0  steps: 5
        // 3rd:   1 1 1 1 1 1 1 1 0  steps: 6
        // 4th:   0 0 0 0 0 0 0 0 0  steps: 8
        //                           total:
        //
        // Each time need to find where heights[i] != heights[i-1], then add i to minSteps

        int minSteps = 0;

        reverseSort(heights);

        for (int i = 1; i < heights.length; i++) {
            if (heights[i] != heights[i - 1]) {
                minSteps += i;
            }
        }

        return minSteps;
    }

    private void reverseSort(int[] heights) {
        Arrays.sort(heights);
        int left = 0, right = heights.length - 1;
        while (left < right) {
            swap(heights, left++, right--);
        }
    }

    private void swap(int[] heights, int l, int r) {
        int tmp = heights[l];
        heights[l] = heights[r];
        heights[r] = tmp;
    }

    public static void main(String[] args) {
        MinStepsToMakeEqualHeight sol = new MinStepsToMakeEqualHeight();

        assertEquals(0, sol.minSteps(new int[] {50}));
        assertEquals(0, sol.minSteps(new int[] {10,10}));
        assertEquals(3, sol.minSteps(new int[] {5,2,1}));
        assertEquals(3, sol.minSteps(new int[] {4,3,1}));
        assertEquals(22, sol.minSteps(new int[] {5,5,5,4,4,3,1,1,0}));
    }
}
