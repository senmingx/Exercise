package com.example.exercise;

import java.util.*;

public class WordSearchII {

    private static final int[][] DIRS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    // Assumptions:
    // 1. board[i][j] is a lowercase English letter.
    // 2. words[i] consists of lowercase English letters.
    // 3. 1 <= m, n <= 12
    // 4. 1 <= words[i].length <= 10, 1 <= words.length <= 3 * 104
    public List<String> findWords(char[][] board, String[] words) {
        int m = board.length, n = board[0].length;
        List<String> wordsFound = new ArrayList<>();

        // 1. Add all words to trie
        // 2. Start with each cell, find in trie startWith prefix to find words
        //    Each time a word is found, delete it from trie to avoid duplicate

        Trie trie = new Trie();
        for (String word : words) {
            trie.insert(word);
        }

        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++) {
                StringBuilder sb = new StringBuilder();
                findWords(board, row, col, sb, trie, wordsFound);
            }
        }

        return wordsFound;
    }

    private void findWords(char[][] board, int row, int col, StringBuilder sb, Trie trie, List<String> wordsFound) {
        if (trie.search(sb.toString())) {
            wordsFound.add(sb.toString());
            trie.delete(sb.toString());
        }
        int m = board.length, n = board[0].length;
        if (row < 0 || row >= m || col < 0 || col >= n || board[row][col] == '*' || !trie.startsWith(sb.toString())) {
            return;
        }

        char cur = board[row][col];
        sb.append(cur);
        board[row][col] = '*';

        for (int[] dir : DIRS) {
            int nextRow = row + dir[0];
            int nextCol = col + dir[1];
            findWords(board, nextRow, nextCol, sb, trie, wordsFound);
        }

        sb.deleteCharAt(sb.length() - 1);
        board[row][col] = cur;
    }
}
