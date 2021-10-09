package com.example.exercise;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LargestKBothExistInArray {
    public int largestK1(int[] arr) {
        // Corner cases
        if (arr == null) {
            throw new IllegalArgumentException();
        }
        if (arr.length < 2) {
            return 0;
        }

        // Records each value in arr
        Set<Integer> set = new HashSet<>();
        for (int num : arr) {
            set.add(num);
        }

        int largestK = 0;

        // For every num in set, if -num exist as well, update largestK if possible
        for (int num : set) {
            if (set.contains(-num)) {
                largestK = Math.max(largestK, Math.abs(num));
            }
        }

        return largestK;
    }

    private int largestK(int[] arr) {
        // Corner cases
        if (arr == null) {
            throw new IllegalArgumentException();
        }
        if (arr.length < 2) {
            return 0;
        }

        // Sort arr first
        Arrays.sort(arr);

        // Two-pointers:
        // Case 1: arr[left] == -arr[right], return arr[right]
        // Case 2: absolute value of arr[left] is greater, left++
        // Case 3: absulute value of arr[right] is greater, right--

        int left = 0, right = arr.length - 1;
        while (left < right) {
            if (arr[left] == -1 * arr[right]) {
                return Math.abs(arr[left]);
            } else if (Math.abs(arr[left]) > Math.abs(arr[right])) {
                left++;
            } else {
                right--;
            }
        }

        return 0;
    }

    public static void main(String[] args) {
        LargestKBothExistInArray sol = new LargestKBothExistInArray();

        assertEquals(3, sol.largestK(new int[] {3,2,-2,5,-3}));
        assertEquals(0, sol.largestK(new int[] {1,2,3,-4}));
    }
}
