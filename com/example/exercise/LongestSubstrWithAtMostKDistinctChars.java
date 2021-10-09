package com.example.exercise;

import java.util.*;

public class LongestSubstrWithAtMostKDistinctChars {
    public int lengthOfLongestSubstringKDistinct(String s, int k) {
        // Corner case 1: invalid input
        if (s == null || k < 0) {
            throw new IllegalArgumentException();
        }
        // Corner case 2: s is empty or k > s.length
        if (s.isEmpty() || k >= s.length()) {
            return s.length();
        }
        // Corner case 3: k = 0
        if (k == 0) {
            return 0;
        }

        // Use a sliding window [left, right) to traverse the string s, and a counter to count distinct chars in window
        // If count <= k, expand window by right++, update longest when possible
        // Else shrink window by left++ til count < k

        Map<Character, Integer> used = new HashMap<>();
        int longest = 0;

        int left = 0;
        for (int right = 0; right < s.length(); right++) {
            // When distinct chars count = k and adding s[right] will increment distinct count
            while (used.size() == k && !used.containsKey(s.charAt(right))) {
                char leftChar = s.charAt(left++);
                if (used.get(leftChar) == 1) {
                    used.remove(leftChar);
                } else {
                    used.put(leftChar, used.get(leftChar) - 1);
                }
            }
            used.put(s.charAt(right), used.getOrDefault(s.charAt(right), 0) + 1);
            longest = Math.max(longest, right - left + 1);
        }

        return longest;
    }

    public static void main(String[] args) {

    }
}
