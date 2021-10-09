package com.example.exercise;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StringWithoutDupFrequency {

    public int cost(String s) {
        if (s == null) {
            throw new IllegalArgumentException();
        }
        if (s.isEmpty()) {
            return 0;
        }

        int cost = 0;

        // 1. Count frequency of each letter
        // 2. For each of 16 letters, find if its count is used, if so, find the next available count until 0

        int[] count = new int[26];
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 'a']++;
        }

        Set<Integer> used = new HashSet<>();
        for (int i = 0; i < count.length; i++) {
            while (count[i] > 0 && used.contains(count[i])) {
                count[i]--;
                cost++;
            }
            if (count[i] > 0) {
                used.add(count[i]);
            }
        }

        return cost;
    }

    public static void main(String[] args) {
        StringWithoutDupFrequency sol = new StringWithoutDupFrequency();

        assertEquals(2, sol.cost("aaabbbcc"));
        assertEquals(3, sol.cost("aaaabbbbccdef"));
        assertEquals(7, sol.cost("abcdefgh"));
    }
}
