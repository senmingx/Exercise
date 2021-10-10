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
        TrieNode root = new TrieNode();

        // 1. Construct trie with all words
        // 2. Start with each cell, DFS from board and trie to find words, once a word is found, remove it from trie

        for (String word : words) {
            TrieNode parent = root;
            for (int i = 0; i < word.length(); i++) {
                char cur = word.charAt(i);
                TrieNode child = parent.children.get(cur);
                if (child == null) {
                    child = new TrieNode();
                    parent.children.put(cur, child);
                }
                parent = child;

                // Mark word in last char
                if (i == word.length() - 1) {
                    parent.word = word;
                }
            }
        }

        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++) {
                if (root.children.containsKey(board[row][col])) {
                    findWords(board, row, col, root, wordsFound);
                }
            }
        }

        return wordsFound;
    }

    private void findWords(char[][] board, int row, int col, TrieNode parent, List<String> wordsFound) {
        char cur = board[row][col];
        TrieNode curNode = parent.children.get(cur);

        if (curNode.word != null) {
            wordsFound.add(curNode.word);
            curNode.word = null;
        }

        int m = board.length, n = board[0].length;
        board[row][col] = '*';

        for (int[] dir : DIRS) {
            int nextRow = row + dir[0], nextCol = col + dir[1];
            if (nextRow >= 0 && nextRow < m && nextCol >= 0 && nextCol < n
                    && curNode.children.containsKey(board[nextRow][nextCol])) {
                findWords(board, nextRow, nextCol, curNode, wordsFound);
            }
        }

        board[row][col] = cur;

        // Remove leaf node, because either word is found or word does not exist in board
        if (curNode.children == null) {
            parent.children.remove(curNode);
        }
    }

    private class TrieNode {
        String word;
        Map<Character, TrieNode> children;

        public TrieNode() {
            children = new HashMap<>();
        }
    }
}
