package com.example.exercise;

public class StringWithoutAAAorBBBGreedy {
    public String strWithout3a3b(int a, int b) {
        // Corner case
        if (a < 0 || b < 0) {
            throw new IllegalArgumentException();
        }

        // Write the most common letter first
        // The only time we don't write most common letter is when last 2 letters are most common letter
        int size = a + b;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            System.out.println(a + "," + b);
            if (a > b) {
                if (a > 0 && (sb.length() < 2 || !(sb.charAt(sb.length() - 1) == 'a' && sb.charAt(sb.length() - 2) == 'a'))) {
                    sb.append('a');
                    a--;
                } else if (b > 0) {
                    sb.append('b');
                    b--;
                }
            } else {
                if (b > 0 && (sb.length() < 2 || !(sb.charAt(sb.length() - 1) == 'b' && sb.charAt(sb.length() - 2) == 'b'))) {
                    sb.append('b');
                    b--;
                } else if (a > 0) {
                    sb.append('a');
                    a--;
                }
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        StringWithoutAAAorBBBGreedy sol = new StringWithoutAAAorBBBGreedy();

//        for (int a = 1; a <= 100; a++) {
//            for (int b = 1; b <= 100; b++) {
//                String res = sol.strWithout3a3b(a, b);
//                System.out.println("(" + a + "," + b + "): " + res);
//            }
//        }

        System.out.println(sol.strWithout3a3b(2, 15));
    }
}
