package com.example.exercise;

import static org.junit.jupiter.api.Assertions.*;
import java.util.HashMap;
import java.util.Map;

public class FindPair {
    // Time: O(n)
    // Space: O(n)
    public boolean allPair1(int[] nums) {
        // Corner case 1: nums is null
        if (nums == null) {
            throw new IllegalArgumentException();
        }
        // Corner case 2: nums is empty
        if (nums.length == 0) {
            return true;
        }
        // Corner case 3: nums len is odd
        if (nums.length % 2 != 0) {
            return false;
        }

        // Key: number, value: number's occurrence
        Map<Integer, Integer> numCount = new HashMap<>();

        // 1. Count the occurrence of numbers in nums
        // 2. Traverse all counts, if any number's occurrence is odd, then this number can not be pair

        for (int num : nums) {
            numCount.put(num, numCount.getOrDefault(num, 0) + 1);
        }

        for (int num : numCount.keySet()) {
            if (numCount.get(num) % 2 != 0) {
                return false;
            }
        }
        return true;
    }

    public boolean allPair(int[] nums) {
        // Corner case 1: nums is null
        if (nums == null) {
            throw new IllegalArgumentException();
        }
        // Corner case 2: nums is empty
        if (nums.length == 0) {
            return true;
        }
        // Corner case 3: nums len is odd
        if (nums.length % 2 != 0) {
            return false;
        }

        // Bit operation: XOR
        // if a and b is pair, then a^b = 0
        int res = 0;
        for (int num : nums) {
            res = res ^ num;
        }

        return res == 0;
    }

    public static void main(String[] args) {

        FindPair fp = new FindPair();

        int[] case1 = null;
        int[] case2 = new int[0];
        int[] case3 = new int[] {1,1,2,2};
        int[] case4 = new int[] {1,1,1,2};
        int[] case5 = new int[] {1,1,1};

//        assertThrows(IllegalArgumentException.class, fp.allPair(case1));
        assertEquals(fp.allPair(case2), true);
        assertEquals(fp.allPair(case3), true);
        assertEquals(fp.allPair(case4), false);
        assertEquals(fp.allPair(case5), false);
    }
}