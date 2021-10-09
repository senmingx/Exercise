package com.example.exercise;

import java.util.Arrays;

// LC 128
public class LongestConsecutiveSequence {
    // Assumption: 0 <= nums.length <= 105, -109 <= nums[i] <= 109
    public int longestConsecutive(int[] nums) {
        // Corner case
        if (nums.length <= 1) {
            return nums.length;
        }

        Arrays.sort(nums);

        int max = 1;
        int curMax = 1;

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i - 1]) {
                if (nums[i] == nums[i - 1] + 1) {
                    curMax += 1;
                } else { // pattern break
                    max = Math.max(max, curMax);
                    curMax = 1;
                }
            }
            // Skip nums[i] == nums[i-1]

            // Last element
            if (i == nums.length - 1) {
                max = Math.max(max, curMax);
            }
        }

        return max;
    }
}
