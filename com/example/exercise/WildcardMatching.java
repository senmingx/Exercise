package com.example.exercise;

public class WildcardMatching {
    // DFS
    public boolean isMatch(String s, String p) {
        // Corner cases
        if (s == null || p == null) {
            throw new IllegalArgumentException();
        }

        Boolean[][] match = new Boolean[s.length() + 1][p.length() + 1];
        return isMatch(s, 0, p, 0, match);
    }

    private boolean isMatch(String s, int si, String p, int pi, Boolean[][] match) {
        // Base cases
        if (pi == p.length()) {
            return si == s.length();
        }
        if (match[si][pi] != null) {
            return match[si][pi];
        }

        // Case 1: p[pi] != '?' or '*', check if s[si] == p[pi]
        // Case 2: p[pi] == '?', continue to check si+1 and pi+1
        // Case 3: p[pi] == '*', check si and pi+n

        if (p.charAt(pi) == '*') { // Case 3
            // * can match 0 or more letters
            for (int i = si; i <= s.length(); i++) {
                if (isMatch(s, i, p, pi + 1, match)) {
                    match[si][pi] = true;
                    return true;
                }
            }
        } else { // Case 1/2
            if (si < s.length() && match(s, si, p, pi)) {
                match[si][pi] = isMatch(s, si + 1, p, pi + 1, match);
                return match[si][pi];
            }
        }

        match[si][pi] = false;
        return false;
    }

    private boolean match(String s, int si, String p, int pi) {
        return p.charAt(pi) == '?' || s.charAt(si) == p.charAt(pi);
    }


    // DP
//    public boolean isMatchDP(String s, String p) {
//        // Corner case
//        if (s == null || p == null) {
//            throw new IllegalArgumentException();
//        }
//
//        // match[i][j]: the first i chars in s match the first j chars in p
//        boolean[][] match = new boolean[s.length() + 1][p.length() + 1];
//
//        // init: match[0][0] = true
//        match[0][0] = true;
//
//        // rule:
//        // 1. s[i-1] == p[j-1] || p[j-1] == '?', then match[i][j] = match[i-1][j-1]
//        // 2. p[j-1] == '*',
//    }
}
