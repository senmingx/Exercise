package com.example.exercise;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MaxLengthOfConcatenatedString {
    public int maxLength(List<String> arr) {
        if (arr == null || arr.isEmpty()) {
            throw new IllegalArgumentException();
        }

        // Extract only string w/o repeating chars
        List<String> uniqueArr = new ArrayList<>();
        for (String str : arr) {
            if (allLetterUnique(str)) {
                uniqueArr.add(str);
            }
        }

        boolean[] used = new boolean[26];
        // DFS
        return maxLength(uniqueArr, used, 0);
    }

    private int maxLength(List<String> uniqueArr, boolean[] used, int index) {
        // Base case: All words are checked
        if (index == uniqueArr.size()) {
            return 0;
        }

        // Two options with uniqueArr[i]
        // 1. Add uniqueArr[i] to concatenated string if possible
        // 2. Not add uniqueArr[i] to concatenated string

        int maxLen = 0;

        // Option 1
        if (noLetterUsed(used, uniqueArr.get(index))) {
            addWord(used, uniqueArr.get(index));
            maxLen = Math.max(maxLen, maxLength(uniqueArr, used, index + 1) + uniqueArr.get(index).length());
            removeWord(used, uniqueArr.get(index));
        }

        // Option 2
        maxLen = Math.max(maxLen, maxLength(uniqueArr, used, index + 1));

        return maxLen;
    }

    private boolean noLetterUsed(boolean[] used, String s) {
        for (int i = 0; i < s.length(); i++) {
            if (used[s.charAt(i) - 'a']) {
                return false;
            }
        }
        return true;
    }

    private void addOrRemoveWord(boolean[] used, String s, boolean add) {
        for (int i = 0; i < s.length(); i++) {
            used[s.charAt(i) - 'a'] = add;
        }
    }

    private void addWord(boolean[] used, String s) {
        addOrRemoveWord(used, s, true);
    }

    private void removeWord(boolean[] used, String s) {
        addOrRemoveWord(used, s, false);
    }

    private boolean allLetterUnique(String str) {
        boolean[] used = new boolean[26];
        for (int i = 0; i < str.length(); i++) {
            if (used[str.charAt(i) - 'a']) {
                return false;
            }
            used[str.charAt(i) - 'a'] = true; // IMPORTANT
        }
        return true;
    }

    public static void main(String[] args) {
        MaxLengthOfConcatenatedString sol = new MaxLengthOfConcatenatedString();

        assertEquals(4, sol.maxLength(Arrays.asList("un", "iq", "ue")));
        assertEquals(6, sol.maxLength(Arrays.asList("cha", "r", "act", "ers")));
        assertEquals(26, sol.maxLength(Arrays.asList("abcdefghijklmnopqrstuvwxyz")));
        assertEquals(0, sol.maxLength(Arrays.asList("yy", "bkhwmpbiisbldzknpm")));
    }
}
