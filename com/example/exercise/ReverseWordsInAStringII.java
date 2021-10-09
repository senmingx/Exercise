package com.example.exercise;

public class ReverseWordsInAStringII {
    public void reverseWords(char[] s) {
        // Corner case
        if (s == null) {
            throw new IllegalArgumentException();
        }

        // 1. Reverse the whole string
        // 2. Reverse each word
        //
        // the sky is blue
        // eulb si yks eht
        // blue is sky the
        //

        reverse(s, 0, s.length - 1);

        int start = 0;
        for (int i = 0; i < s.length; i++) {
            // Start of word: i == 0 or s[i-1] == ' ' and s[i] != ' '
            // End of word: i == s.length - 1 or s[i] != ' ' and s[i+1] = ' '
            if (i == 0 || s[i-1] == ' ' && s[i] != ' ') {
                start = i;
            } else if (i == s.length - 1 || s[i] != ' ' && s[i + 1] == ' ') {
                reverse(s, start, i);
            }
        }
    }

    private void reverse(char[] s, int left, int right) {
        while (left < right) {
            swap(s, left++, right--);
        }
    }

    private void swap(char[] s, int l, int r) {
        char tmp = s[l];
        s[l] = s[r];
        s[r] = tmp;
    }

    public static void main(String[] args) {
        ReverseWordsInAStringII sol = new ReverseWordsInAStringII();
        char[] s = new String("i am such a baby").toCharArray();
        sol.reverseWords(s);
        printArr(s);
    }

    private static void printArr(char[] s) {
        for (char c : s) {
            System.out.print(c);
        }
    }
}
