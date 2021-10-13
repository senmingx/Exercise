package com.example.exercise;

public class IntegerToRoman {

    private static final String[] SYMBOLS = new String[] { "I", "IV", "V", "IX", "X", "XL", "L", "XC", "C", "CD", "D", "CM", "M"};
    private static final int[] VALUES = new int[] { 1, 4, 5, 9, 10, 40, 50, 90, 100, 400, 500, 900, 1000 };

    public String intToRoman(int num) {
        StringBuilder roman = new StringBuilder();
        while (num > 0) {
            // 1. Find the index of greatest smaller than or equal to num in VALUES
            // 2. Append SYMBOLS[index] to roman, num -= VALUES[index]
            int greatestSmallerOrEqualIndex = greatestSmallerOrEqualIndex(num);
            roman.append(SYMBOLS[greatestSmallerOrEqualIndex]);
            num -= VALUES[greatestSmallerOrEqualIndex];
        }
        return roman.toString();
    }

    private int greatestSmallerOrEqualIndex(int num) {
        int start = 0, end = VALUES.length - 1;
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (VALUES[mid] <= num) {
                start = mid;
            } else {
                end = mid;
            }
        }
        return VALUES[end] <= num ? end : start;
    }

    public static void main(String[] args) {
        IntegerToRoman sol = new IntegerToRoman();
        System.out.println(sol.intToRoman(3));
        System.out.println(sol.intToRoman(4));
        System.out.println(sol.intToRoman(9));
        System.out.println(sol.intToRoman(58));
        System.out.println(sol.intToRoman(1994));
    }
}
