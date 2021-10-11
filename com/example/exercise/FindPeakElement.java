package com.example.exercise;

// LC 162
public class FindPeakElement {
    // Assumptions:
    // 1. 1 <= nums.length <= 1000
    // 2. -2^31 <= nums[i] <= 2^31 - 1
    // 3. nums[i] != nums[i + 1] for all valid i.
    public int findPeakElement(int[] nums) {
        // Corner cases
        if (nums.length < 2) {
            return 0;
        }

        int start = 0, end = nums.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            int leftVal = mid == 0 ? Integer.MIN_VALUE : nums[mid - 1];
            int rightVal = mid == nums.length - 1 ? Integer.MIN_VALUE : nums[mid + 1];

            if (nums[mid] > leftVal && nums[mid] > rightVal) { // Peak
                return mid;
            } else if (nums[mid] > leftVal && rightVal > nums[mid]) { // Trending up, peak at right
                start = mid;
            } else if (nums[mid] > rightVal && leftVal > nums[mid]) { // Trending down, peak at left
                end = mid;
            } else { // Valley, peak at left and right, let's go right
                start = mid;
            }
        }

        return nums[start] > nums[end] ? start : end;
    }
}
