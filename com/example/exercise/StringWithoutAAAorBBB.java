package com.example.exercise;

import java.util.*;

public class StringWithoutAAAorBBB {
    public String strWithout3a3b(int a, int b) {
        if (a < 0 || b < 0) {
            throw new IllegalArgumentException();
        }

        Map<String, String> mem = new HashMap<>();
        return strWithout3a3b(new StringBuilder(), a, b, mem);
    }

    private String strWithout3a3b(StringBuilder path, int a, int b, Map<String, String> mem) {
        // Base case 1: use all a and b
        if (a == 0 && b == 0) {
            return path.toString();
        }
        // Base case 2: already in memory
        if (mem.get(path.toString()) != null) {
            return mem.get(path.toString());
        }

        // Next char can be a or b
        // If last 2 chars in path is not 'aa', then we can add a
        // If last 2 chars in path is not 'bb', then we can add b

        int len = path.length();

        if (a > 0 && (len < 2 || !path.substring(len - 2).equals("aa"))) {
            path.append('a');
            String possibleStr = strWithout3a3b(path, a - 1, b, mem);
            if (!possibleStr.isEmpty()) {
                mem.put(path.toString(), possibleStr);
                return possibleStr;
            }
            path.setLength(len);
        }

        if (b > 0 && (len < 2 ||!path.substring(len - 2).equals("bb"))) {
            path.append('b');
            String possibleStr = strWithout3a3b(path, a, b - 1, mem);
            if (!possibleStr.isEmpty()) {
                mem.put(path.toString(), possibleStr);
                return possibleStr;
            }
            path.setLength(len);
        }

        mem.put(path.toString(), "");
        return "";
    }

    public static void main(String[] args) {
        StringWithoutAAAorBBB sol = new StringWithoutAAAorBBB();

//        for (int a = 1; a <= 100; a++) {
//            for (int b = 1; b <= 100; b++) {
//                String res = sol.strWithout3a3b(a, b);
//                System.out.println("(" + a + "," + b + "): " + res);
//            }
//        }

        System.out.println(sol.strWithout3a3b(20, 40));
    }
}
