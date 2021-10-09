package com.example.exercise;

public class ValidSudoku {

    private static final int LEN = 9;

    private boolean[][] rowUsed = new boolean[LEN][LEN + 1];
    private boolean[][] colUsed = new boolean[LEN][LEN + 1];
    private boolean[][] gridUsed = new boolean[LEN][LEN + 1];

    public boolean isValidSudoku(char[][] board) {
        if (board == null || board.length != 9 && board[0] == null && board[0].length != 9) {
            throw new IllegalArgumentException();
        }

        for (int row = 0; row < LEN; row++) {
            for (int col = 0; col < LEN; col++) {
                char cur = board[row][col];
                if (board[row][col] != '.') {
                    int num = cur - '0';
                    if (canPlaceNumber(num, row, col)) {
                        placeNumber(board, num, row, col);
                    } else {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    private void placeNumber(char[][] board, int num, int row, int col) {
        board[row][col] = (char)('0' + num);
        rowUsed[row][num] = true;
        colUsed[col][num] = true;
        gridUsed[row / 3 * 3 + col / 3][num] = true;
    }

    private boolean canPlaceNumber(int num, int row, int col) {
        return !rowUsed[row][num] && !colUsed[col][num] && !gridUsed[row / 3 * 3 + col / 3][num];
    }
}
