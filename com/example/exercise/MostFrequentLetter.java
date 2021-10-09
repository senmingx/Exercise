package com.example.exercise;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MostFrequentLetter {

    public char mostFreqentLetter(String s) {
        int[] count = new int[26];
        char[] letters = s.toCharArray();
        int maxFrequency = 0;

        // count frequency of letters
        for (int i = 0; i < letters.length; i++) {
            count[letters[i] - 'a']++;
            maxFrequency = Math.max(maxFrequency, count[letters[i] - 'a']);
        }

        for (int i = 0; i < count.length; i++) {
            // the first one with max frequency is the first in alphabet order
            if (maxFrequency == count[i]) {
                return (char)('a' + i);
            }
        }

        return 'a';
    }

    public static void main(String[] args) {
        MostFrequentLetter sol = new MostFrequentLetter();
        assertEquals('a', sol.mostFreqentLetter("bbbaaa"));
        assertEquals('d', sol.mostFreqentLetter("abcdd"));
    }
}
