package com.example.exercise;

// LC 79
public class WordSearch {

    private static final int[][] DIRS = new int[][] {{1,0}, {-1,0}, {0,1}, {0,-1}};

    // Assumptions:
    // 1. board and word consists of only lowercase and uppercase English letters.
    // 2. m == board.length, n = board[i].length, 1 <= m, n <= 6, 1 <= word.length <= 15
    public boolean exist(char[][] board, String word) {
        int m = board.length, n = board[0].length;
        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++) {
                // DON'T return exist(...) because we need to search all starts
                if (exist(board, row, col, word, 0)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean exist(char[][] board, int row, int col, String word, int index) {
        int m = board.length, n = board[0].length;

        // Base case 1: index == word.length(), means word is found
        if (index == word.length()) {
            return true;
        }
        // Base case 2: row, col out of bound, or board[row][col] != word[index]
        if (row < 0 || row >= m || col < 0 || col >= n || board[row][col] != word.charAt(index)) {
            return false;
        }


        char cur = board[row][col];
        // Mark (row, col) as visited
        board[row][col] = '.';

        // Try 4 directions to find word with next index
        for (int[] dir : DIRS) {
            int nextRow = row + dir[0];
            int nextCol = col + dir[1];
            if (exist(board, nextRow, nextCol, word, index + 1)) {
                return true;
            }
        }

        // Backtracking: recover char in (row, col)
        board[row][col] = cur;

        // When try all 4 directions but still can't find the word, then return false
        return false;
    }
}
