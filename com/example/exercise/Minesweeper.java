package com.example.exercise;

public class Minesweeper {
    public char[][] updateBoard(char[][] board, int[] click) {
        int row = click[0], col = click[1];
        // Base cases
        if (board[row][col] == 'M') {
            board[row][col] = 'X';
            return board;
        }

        int count = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                int nextRow = row + i;
                int nextCol = col + j;
                if (nextRow >= 0 & nextRow < board.length && nextCol >= 0 && nextCol < board[0].length && board[nextRow][nextCol] == 'M') {
                    count++;
                }
            }
        }

        if (count > 0) {
            board[row][col] = (char)('0' + count);
        } else {
            board[row][col] = 'B';
            for (int i = -1; i <= 1; i++) {
                for (int j = -1; j <= 1; j++) {
                    int nextRow = row + i;
                    int nextCol = col + j;
                    if (nextRow >= 0 & nextRow < board.length && nextCol >= 0 && nextCol < board[0].length && board[nextRow][nextCol] == 'E') {
                        updateBoard(board, new int[] {nextRow, nextCol});
                    }
                }
            }
        }

        return board;
    }
}
