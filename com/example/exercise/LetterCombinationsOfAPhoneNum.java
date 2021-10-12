package com.example.exercise;

import java.util.*;

// LC 17
public class LetterCombinationsOfAPhoneNum {

    private static final String[] LETTERS = new String[] { "", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz" };

    // Assumptions:
    // 1. 0 <= digits.length <= 4
    // 2. digits[i] is a digit in the range ['2', '9'].
    public List<String> letterCombinations(String digits) {
        List<String> comb = new ArrayList<>();
        if (digits.isEmpty()) {
            return comb;
        }

        StringBuilder path = new StringBuilder();
        letterCombinations(digits, 0, comb, path);

        return comb;
    }

    private void letterCombinations(String digits, int index, List<String> comb, StringBuilder path) {
        // Base case
        if (index == digits.length()) {
            comb.add(path.toString());
            return;
        }

        // For number digits[index], we can try each char in LETTERS[number]
        int num = digits.charAt(index) - '0';
        for (int i = 0; i < LETTERS[num].length(); i++) {
            path.append(LETTERS[num].charAt(i));
            // Try next digit
            letterCombinations(digits, index + 1, comb, path);
            path.deleteCharAt(path.length() - 1);
        }
    }

    public static void main(String[] args) {
        LetterCombinationsOfAPhoneNum sol = new LetterCombinationsOfAPhoneNum();
        System.out.println(sol.letterCombinations("23"));
        System.out.println(sol.letterCombinations(""));
        System.out.println(sol.letterCombinations("2"));
        System.out.println(sol.letterCombinations("392"));
    }
}
