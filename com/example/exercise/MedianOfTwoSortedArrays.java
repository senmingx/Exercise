package com.example.exercise;

public class MedianOfTwoSortedArrays {

    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // Corner case: Null or both empty
        if (nums1 == null || nums2 == null || nums1.length == 0 && nums2.length == 0) {
            throw new IllegalArgumentException();
        }

        // Case 1: size % 2 == 0, take avg of size/2 - 1 and size/2
        // Case 2: size % 2 == 1, take size/2

        int size = nums1.length + nums2.length;
        if (size % 2 == 0) {
            return (kthSmallest(nums1, 0, nums2, 0, size / 2 - 1) + kthSmallest(nums1, 0, nums2, 0, size / 2)) / 2.0;
        } else {
            return kthSmallest(nums1, 0, nums2, 0, size / 2);
        }
    }

    // k is 0-based
    //
    // Quick selection
    // Time: O(logk)
    // Space: O(1)
    private double kthSmallest(int[] nums1, int start1, int[] nums2, int start2, int k) {
        // Base case 1: index1 out of bound
        if (start1 >= nums1.length) {
            return nums2[start2 + k];
        }
        // Base case 2: index2 out of bound
        if (start2 >= nums2.length) {
            return nums1[start1 + k];
        }
        // Base case 3: k == 0
        if (k == 0) {
            return Math.min(nums1[start1], nums2[start2]);
        }

        int index1 = start1 + k / 2;
        int index2 = start2 + k - k / 2;
        int val1 = index1 < nums1.length ? nums1[index1] : Integer.MAX_VALUE;
        int val2 = index2 < nums2.length ? nums2[index2] : Integer.MAX_VALUE;

        // Case 1: val1 < val2, nums1[start1... start1 + k/2] will be smaller than kth, can be eliminated
        // Case 2: val1 >= val2, similar situation as case 1
        if (val1 < val2) {
            return kthSmallest(nums1, index1 + 1, nums2, start2, k - k / 2);
        } else {
            return kthSmallest(nums1, index1, nums2, index2 + 1, k / 2);
        }
    }

    // k is 0-based
    //
    // Two pointers
    // Time: O(k)
    // Space: O(1)
    private int kthSmallest1(int[] nums1, int index1, int[] nums2, int index2, int k) {
        while (index1 < nums1.length && index2 < nums2.length && k-- > 0) {
            if (nums1[index1] < nums2[index2]) {
                index1++;
            } else {
                index2++;
            }
        }
        while (index1 < nums1.length && k-- > 0) {
            index1++;
        }
        while (index2 < nums2.length && k-- > 0) {
            index2++;
        }

        if (index1 < nums1.length && index2 < nums2.length) {
            return Math.min(nums1[index1], nums2[index2]);
        } else if (index1 < nums1.length) {
            return nums1[index1];
        } else {
            return nums2[index2];
        }
    }
}
