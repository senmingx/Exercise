package com.example.exercise;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StringCompression {

    public int compress(char[] chars) {
        if (chars == null || chars.length == 0) {
            throw new IllegalArgumentException();
        }

        // a a b b c c c
        //               i
        //
        // count = 3
        // cur = c
        // res = a2b2c3

        // For each i,
        // if chars[i] == chars[i-1], count++
        // else add cur + count (if > 1) to res, then reset count=1, cur=chars[i]

        // Use chars first compressionIndex letters as result
        int compressionIndex = 0;

        int count = 1; // number of consecutive occurence of cur
        char cur = chars[0];

        for (int i = 1; i <= chars.length; i++) {
            if (i < chars.length && chars[i] == chars[i - 1]) {
                count++;
            } else {
                String group = count > 1 ? cur + "" + count : cur + "";
                for (int j = 0; j < group.length(); j++) {
                    chars[compressionIndex++] = group.charAt(j);
                }

                if (i < chars.length) { // Avoid index out of bound
                    cur = chars[i];
                    count = 1;
                }
            }
        }

        return compressionIndex;
    }

    public static void main(String[] args) {
        StringCompression sol = new StringCompression();

        assertEquals(6, sol.compress(new char[] {'a', 'a', 'b', 'b', 'c', 'c', 'c'}));
        assertEquals(1, sol.compress(new char[] {'a'}));
        assertEquals(4, sol.compress(new char[] {'a','b','b','b','b','b','b','b','b','b','b','b','b'}));
    }
}
