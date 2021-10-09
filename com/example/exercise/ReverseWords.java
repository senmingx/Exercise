package com.example.exercise;

public class ReverseWords {
    public String reverseWords(String s) {
        // Corner cases
        if (s == null) {
            throw new IllegalArgumentException();
        }
        if (s.isEmpty()) {
            return s;
        }

        // Remove reluctant spaces (leading, trailing, more than 1 in middle)
        char[] arr = removeSpaces(s).toCharArray();

        // 1. Reverse the whole string
        // 2. Reverse each word
        //
        // E.g. the sky is a blue
        //   1. elub si yks a eht
        //   2. blue is sky a the

        reverse(arr, 0, arr.length - 1);

        int start = 0; // index of start of a word
        for (int i = 0; i < arr.length; i++) {
            // Start of a word: i == 0 || arr[i] != ' ' && arr[i - 1] == ' '
            // End of a word: i is last index or arr[i] != ' ' && arr[i + 1] == ' '

            if (i == 0 || arr[i] != ' ' && arr[i - 1] == ' ') {
                start = i;
            } else if (i == arr.length - 1 || arr[i] != ' ' && arr[i + 1] == ' ') {
                reverse(arr, start, i);
            }
        }

        return new String(arr);
    }

    private String removeSpaces(String s) {
        char[] arr = s.toCharArray();

        // _ _ _ a _ a b c _ _
        // a _ a b c _
        //             s
        //                   f

        int slow = 0;
        // Keep only one space after word
        for (int fast = 0; fast < arr.length; fast++) {
            if (arr[fast] != ' ' || fast > 0 && arr[fast] == ' ' && arr[fast - 1] != ' ') {
                arr[slow++] = arr[fast];
            }
        }
        // Remove last space if it has
        if (arr[slow - 1] == ' ') {
            slow--;
        }

        return new String(arr, 0, slow);
    }

    private void reverse(char[] arr, int start, int end) {
        while (start < end) {
            char tmp = arr[start];
            arr[start] = arr[end];
            arr[end] = tmp;
            start++;
            end--;
        }
    }
}
