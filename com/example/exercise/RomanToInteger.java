package com.example.exercise;

import java.util.HashMap;
import java.util.Map;

public class RomanToInteger {

    private static final char[] SYMBOLS = new char[] { 'I', 'V', 'X', 'L', 'C', 'D', 'M' };
    private static final int[] VALUES = new int[] { 1, 5, 10, 50, 100, 500, 1000 };

    public int romanToInt(String s) {
        // Symbol to value mapping
        Map<Character, Integer> symbolToVal = new HashMap<>();
        for (int i = 0; i < SYMBOLS.length; i++) {
            symbolToVal.put(SYMBOLS[i], VALUES[i]);
        }

        int total = 0;
        // s, from left to right, should be decreasing
        for (int i = 0; i < s.length(); i++) {
            int curVal = symbolToVal.get(s.charAt(i));
            int nextVal = i + 1 < s.length() ? symbolToVal.get(s.charAt(i + 1)) : 0;
            if (nextVal > curVal) {
                total += nextVal - curVal;
                i++;
            } else {
                total += curVal;
            }
        }
        return total;
    }

    public static void main(String[] args) {
        RomanToInteger sol = new RomanToInteger();
        System.out.println(sol.romanToInt("III"));
        System.out.println(sol.romanToInt("IV"));
        System.out.println(sol.romanToInt("IX"));
        System.out.println(sol.romanToInt("LVIII"));
        System.out.println(sol.romanToInt("MCMXCIV"));
    }
}
