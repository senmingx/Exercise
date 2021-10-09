package com.example.exercise;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

// LC 438
public class FindAllAnagramsInAString {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> anagrams = new ArrayList<>();

        // Use a sliding window with size of p's length,
        // [i-len+1, i]
        // If sliding window is anagram, add to result

        int[] countS = new int[26];
        int[] countP = new int[26];

        for (int i = 0; i < p.length(); i++) {
            countP[p.charAt(i) - 'a']++;
        }

        for (int i = 0; i < s.length(); i++) {
            if (i < p.length()) {
                countS[s.charAt(i) - 'a']++;
            } else {
                if (match(countS, countP)) {
                    anagrams.add(i - p.length());
                }
                countS[s.charAt(i) - 'a']++;
                countS[s.charAt(i - p.length()) - 'a']--;
            }

            if (i == s.length() - 1) {
                if (match(countS, countP)) {
                    anagrams.add(i - p.length() + 1);
                }
            }
        }

        return anagrams;
    }

    private boolean match(int[] countS, int[] countP) {
        for (int i = 0; i < countS.length; i++) {
            if (countS[i] != countP[i]) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        FindAllAnagramsInAString sol = new FindAllAnagramsInAString();

        assertEquals(Arrays.asList(0,6), sol.findAnagrams("cbaebabacd", "abc"));
        assertEquals(Arrays.asList(0,1,2), sol.findAnagrams("abab", "ab"));
        assertEquals(Arrays.asList(0), sol.findAnagrams("abc", "abc"));
    }
}
