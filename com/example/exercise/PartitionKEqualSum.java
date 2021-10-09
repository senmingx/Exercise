package com.example.exercise;

public class PartitionKEqualSum {
    public boolean canPartitionKSubsets(int[] nums, int k) {
        // corner
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % k != 0) {
            return false;
        }

        int[] sets = new int[k];
        return canPartitionKSubsets(nums, 0, sets, sum / k);
    }

    private boolean canPartitionKSubsets(int[] nums, int index, int[] sets, int targetSum) {
        // base case
        if (index == nums.length) {
            return equalSum(sets);
        }

        // nums[index] should fit into one of sets, if it can not fit into any, then return false
        for (int i = 0; i < sets.length; i++) {
            if (sets[i] + nums[index] <= targetSum) {
                sets[i] += nums[index];
                if (canPartitionKSubsets(nums, index + 1, sets, targetSum)) {
                    return true;
                }
                sets[i] -= nums[index];
            }
        }

        return false;
    }

    private boolean equalSum(int[] sets) {
        int sum = sets[0];
        for (int num : sets) {
            if (num != sum) {
                return false;
            }
        }
        return true;
    }
}
