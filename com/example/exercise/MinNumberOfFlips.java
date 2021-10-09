package com.example.exercise;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MinNumberOfFlips {
    public int minFlips(String s) {
        if (s == null || s.isEmpty()) {
            throw new IllegalArgumentException();
        }

        int minFlips = s.length();
        int n = s.length();

        // Append s to s
        // E.g. s = "111000"
        // s' = s + s = "111000111000"
        //
        // Use sliding window with size n
        // 11 [100011] 1000
        // Each time sliding window move right, equals to type-1 operation
        // Then we just need to calculate the number of type-2 operation in each sliding window and get the min
        //
        // We can flip sliding window into either "101010.." or "010101.."

        s = s + s;
        StringBuilder case1Sb = new StringBuilder(); // case 1: 010101
        StringBuilder case2Sb = new StringBuilder(); // case 2: 101010
        // Length of case 1/2 should match s after appending for easy comparison
        for (int i = 0; i < s.length(); i++) {
            if (i % 2 == 0) {
                case1Sb.append('0');
                case2Sb.append('1');
            } else {
                case1Sb.append('1');
                case2Sb.append('0');
            }
        }
        String case1 = case1Sb.toString();
        String case2 = case2Sb.toString();

        int numFlip1 = 0; // number of flips to change sliding window to case1
        int numFlip2 = 0; // number of flips to change sliding window to case2

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) != case1.charAt(i)) {
                numFlip1++;
            }
            if (s.charAt(i) != case2.charAt(i)) {
                numFlip2++;
            }

            // Case: s[i-n] is out of sliding window
            if (i >= n) {
                if (s.charAt(i - n) != case1.charAt(i - n)) {
                    numFlip1--;
                }
                if (s.charAt(i - n) != case2.charAt(i - n)) {
                    numFlip2--;
                }
            }

            // Case: traverse whole sliding window, should compare to get minFlip
            if (i >= n - 1) {
                minFlips = Math.min(minFlips, Math.min(numFlip1, numFlip2));
            }
        }

        return minFlips;
    }

    public static void main(String[] args) {
        MinNumberOfFlips sol = new MinNumberOfFlips();

        assertEquals(2, sol.minFlips("111000"));
        assertEquals(0, sol.minFlips("010"));
        assertEquals(1, sol.minFlips("1110"));
    }
}
