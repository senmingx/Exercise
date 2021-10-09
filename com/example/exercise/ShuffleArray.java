package com.example.exercise;

import java.util.*;

public class ShuffleArray {

    private int[] array;
    private int[] original;

    public ShuffleArray(int[] nums) {
        array = nums.clone();
        original = nums.clone();
    }

    // O(n)
    public int[] reset() {
        array = original.clone();
        return array;
    }

    // O(n)
    public int[] shuffle() {
        for (int i = 0; i < array.length; i++) {
            // Randomly choose an index between i and array.length - 1,
            // Swap i and index in array
            int randIndex = i + (int)(Math.random() * (array.length - i));
            swap(array, i, randIndex);
        }
        return array;
    }

    private void swap(int[] nums, int i1, int i2) {
        int tmp = nums[i1];
        nums[i1] = nums[i2];
        nums[i2] = tmp;
    }

}
