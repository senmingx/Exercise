package com.example.exercise;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordSearchII {

    private static final int[][] DIRS = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    // Number of words: m
    // Word length: n
    //
    // Time: O(m * 4^n)
    // space: O(row * col)
    public List<String> findWords(char[][] board, String[] words) {
        List<String> res = new ArrayList<>();
        // Corner cases
        if (board == null || board.length == 0 || board[0] == null || board[0].length == 0 || words == null) {
            throw new IllegalArgumentException();
        }
        if (words.length == 0) {
            return res;
        }

        for (String word : words) {
            if (findWord(board, word)) {
                res.add(word);
            }
        }

        return res;
    }

    private boolean findWord(char[][] board, String word) {
        int rows = board.length, cols = board[0].length;
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                boolean[][] visited = new boolean[rows][cols];
                if (findWord(board, row, col, word, 0, visited)) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean findWord(char[][] board, int row, int col, String word, int index, boolean[][] visited) {
        int rows = board.length, cols = board[0].length;
        // Base case 1: Found word (Put this first as we could be out of bound but still found word)
        if (index == word.length()) {
            return true;
        }
        // Base case 0: Our of bound or Already visited
        if (row < 0 || row >= rows || col < 0 || col >= cols || visited[row][col]) {
            return false;
        }
        // Base case 2: Letter not match
        if (board[row][col] != word.charAt(index)) {
            return false;
        }

        visited[row][col] = true;

        // Try each direction
        for (int[] dir : DIRS) {
            int nextRow = row + dir[0];
            int nextCol = col + dir[1];
            if (findWord(board, nextRow, nextCol, word, index + 1, visited)) {
                return true;
            }
        }

        visited[row][col] = false;

        return false;
    }

    public static void main(String[] args) {
        WordSearchII sol = new WordSearchII();

    }
}
