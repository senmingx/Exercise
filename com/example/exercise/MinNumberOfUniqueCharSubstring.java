package com.example.exercise;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MinNumberOfUniqueCharSubstring {
    public int minNumberOfUniqueCharSubstring(String s) {
        if (s == null || s.isEmpty()) {
            throw new IllegalArgumentException();
        }

        // init minNumOfUniqueCharSubstring to 1 because at least 1 substring exist, then each split
        // will increase substring count by 1
        int minNumOfUniqueCharSubstring = 1;

        // Use a sliding window to traverse the string,
        // If no repeating chars in window so far, expand window by right++
        // Otherwise split string and reset window, increment substring count minNumOfUniqueCharSubstring

        boolean[] used = new boolean[26];
        for (int i = 0; i < s.length(); i++) {
            char cur = s.charAt(i);
            if (used[cur - 'a']) {
                used = new boolean[26];
                minNumOfUniqueCharSubstring++;
            }
            used[cur - 'a'] = true;
        }

        return minNumOfUniqueCharSubstring;
    }

    public static void main(String[] args) {
        MinNumberOfUniqueCharSubstring sol = new MinNumberOfUniqueCharSubstring();

        assertEquals(3, sol.minNumberOfUniqueCharSubstring("abacdec"));
        assertEquals(1, sol.minNumberOfUniqueCharSubstring("world"));
        assertEquals(4, sol.minNumberOfUniqueCharSubstring("dddd"));
        assertEquals(2, sol.minNumberOfUniqueCharSubstring("cycle"));
        assertEquals(2, sol.minNumberOfUniqueCharSubstring("abba"));
    }
}
