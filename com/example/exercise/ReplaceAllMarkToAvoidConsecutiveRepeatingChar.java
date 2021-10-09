package com.example.exercise;

// LC 1576
public class ReplaceAllMarkToAvoidConsecutiveRepeatingChar {
    public String modifyString(String s) {
        // Corner case
        if (s == null) {
            throw new IllegalArgumentException();
        }
        if (s.isEmpty()) {
            return s;
        }

        char[] arr = s.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == '?') {
                for (char c = 'a'; c <= 'z'; c++) {
                    if (noRepeating(arr, i, c)) {
                        arr[i] = c;
                        break;
                    }
                }
            }
        }

        return new String(arr);
    }

    private boolean noRepeating(char[] arr, int index, char c) {
        return (index == 0 || arr[index - 1] != c) && (index == arr.length - 1 || arr[index + 1] != c);
    }
}
