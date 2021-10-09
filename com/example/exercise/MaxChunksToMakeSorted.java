package com.example.exercise;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MaxChunksToMakeSorted {
    public int maxChunksToSorted(int[] arr) {
        // Corner cases
        if (arr == null) {
            throw new IllegalArgumentException();
        }
        if (arr.length == 0) {
            return 0;
        }

        // Range of arr[i]: 0 to n-1
        //
        // If connected each chunk to make array sorted, then for chunk[i,...j], it must contain only
        // values from i to j, max of chunk[i,...j] is j
        //
        // original: 0 2 1 4 3 5 7 6
        // sorted:   0 1 2 3 4 5 6 7
        // max:      0 2 2 4 4 5 7 7
        //
        // Split when sorter[i] = max[i] => i = max[i]

        int[] max = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            max[i] = i == 0 ? arr[0] : Math.max(max[i-1], arr[i]);
        }

        int numOfChunks = 0;
        for (int i = 0; i < max.length; i++) {
            if (max[i] == i) {
                numOfChunks++;
            }
        }

        return numOfChunks;
    }

    public static void main(String[] args) {
        MaxChunksToMakeSorted sol = new MaxChunksToMakeSorted();

        assertEquals(1, sol.maxChunksToSorted(new int[] {4,3,2,1,0}));
        assertEquals(4, sol.maxChunksToSorted(new int[] {1,0,2,3,4}));
    }
}
