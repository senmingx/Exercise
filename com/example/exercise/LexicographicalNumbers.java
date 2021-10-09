package com.example.exercise;

import java.util.*;

public class LexicographicalNumbers {
    public List<Integer> lexicalOrder(int n) {
        List<Integer> nums = new ArrayList<>();

        for (int i = 1; i <= 9; i++) {
            dfs(i, n, nums);
        }

        return nums;
    }

    private void dfs(int num, int n, List<Integer> nums) {
        if (num > n) {
            return;
        }

        nums.add(num);

        for (int i = 0; i <= 9; i++) {
            int nextNum = num * 10 + i;
            if (nextNum <= n) dfs(nextNum, n, nums);
        }
    }
}
