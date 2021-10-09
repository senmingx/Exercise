package com.example.exercise;

public class MinMovesToEqualArrayII {
    // Quick selection to find medium
    public int minMoves2(int[] nums) {
        // Corner cases
        if (nums == null) {
            throw new IllegalArgumentException();
        }
        if (nums.length < 2) {
            return 0;
        }

        int minMoves = 0;

        int medium = quickSelect(nums, 0, nums.length - 1, nums.length / 2);
        for (int num : nums) {
            minMoves += Math.abs(num - medium);
        }

        return minMoves;
    }

    private int quickSelect(int[] nums, int start, int end, int targetIndex) {
        // Base case
        if (start == end) {
            return nums[start];
        }

        int pivotIndex = partition(nums, start, end, targetIndex);

        // After partition, nums[0..pivotInedx - 1] < pivot, nums[pivotIndex + 1, end] > pivot
        if (pivotIndex == targetIndex) {
            return nums[pivotIndex];
        } else if (pivotIndex < targetIndex) {
            return quickSelect(nums, pivotIndex + 1, end, targetIndex);
        } else {
            return quickSelect(nums, start, pivotIndex - 1, targetIndex);
        }
    }

    private int partition(int[] nums, int start, int end, int targetIndex) {
        int pivotIndex = start + (int)(Math.random() * (end - start + 1));
        int pivot = nums[pivotIndex];

        swap(nums, pivotIndex, end);

        int left = start, right = end - 1;
        while (left <= right) {
            if (nums[left] <= pivot) {
                left++;
            } else if (nums[right] > pivot) {
                right--;
            } else {
                swap(nums, left++, right--);
            }
        }
        // After: left = right + 1, and nums[start, left) <= pivot
        // So swap pivot to left, so that also nums[start, left] <= pivot
        swap(nums, left, end);

        return left;
    }

    private void swap(int[] nums, int i1, int i2) {
        int tmp = nums[i1];
        nums[i1] = nums[i2];
        nums[i2] = tmp;
    }
}
