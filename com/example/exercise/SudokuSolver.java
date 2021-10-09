package com.example.exercise;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SudokuSolver {

    // Length of Sudoku board
    static final int LEN = 9;

    boolean solved = false;

    boolean[][] rowUsed = new boolean[LEN][LEN + 1];
    boolean[][] colUsed = new boolean[LEN][LEN + 1];
    boolean[][] gridUsed = new boolean[LEN][LEN + 1];

    /**
     * Solve sudoku
     * @param board
     */
    public void solveSudoku(char[][] board) {
        // Corner case
        if (board == null || board.length != LEN || board[0] == null || board[0].length != LEN) {
            throw new IllegalArgumentException();
        }

        // Get empty cells
        List<Pair<Integer, Integer>> emptyCells = getEmptyCells(board);

        // Fill each empty cells start with index 0
        solveSudoku(board, emptyCells, 0);
    }

    private void solveSudoku(char[][] board, List<Pair<Integer, Integer>> emptyCells, int index) {
        // Base case: All empty cells filled
        if (index == emptyCells.size()) {
            solved = true;
            return;
        }

        int row = emptyCells.get(index).getKey();
        int col = emptyCells.get(index).getValue();
        int grid = row / 3 * 3 + col / 3;
        for (int i = 1; i <= 9; i++) {
            if (canPlaceNumber(i, row, col, grid)) {
                placeNumber(board, i, row, col, grid);
                solveSudoku(board, emptyCells, index + 1);
                if (solved) {
                    return;
                }
                removeNumber(board, i, row, col, grid);
            }
        }
    }

    private void removeNumber(char[][] board, int num, int row, int col, int grid) {
        board[row][col] = '.';
        rowUsed[row][num] = false;
        colUsed[col][num] = false;
        gridUsed[row / 3 * 3 + col / 3][num] = false;
    }

    private void placeNumber(char[][] board, int num, int row, int col, int grid) {
        board[row][col] = (char)('0' + num);
        rowUsed[row][num] = true;
        colUsed[col][num] = true;
        gridUsed[row / 3 * 3 + col / 3][num] = true;
    }

    private boolean canPlaceNumber(int num, int row, int col, int grid) {
        return !rowUsed[row][num] && !colUsed[col][num] && !gridUsed[row / 3 * 3 + col / 3][num];
    }

    private List<Pair<Integer, Integer>> getEmptyCells(char[][] board) {
        List<Pair<Integer, Integer>> empty = new ArrayList<>();
        for (int i = 0; i < LEN; i++) {
            for (int j = 0; j < LEN; j++) {
                if (board[i][j] == '.') {
                    empty.add(new Pair<>(i, j));
                } else {
                    int num = board[i][j] - '0';
                    placeNumber(board, num, i, j, i / 3 * 3 + j / 3);
                }
            }
        }
        return empty;
    }

    public static void main(String[] args) {
        SudokuSolver sol = new SudokuSolver();
        char[][] matrix = generate();
        sol.solveSudoku(matrix);
        printMatrix(matrix);
    }

    private static void printMatrix(char[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static char[][] generate() {
        char[][] matrix = new char[LEN][LEN];
        for (int i = 0; i < LEN; i++) {
            Arrays.fill(matrix[i], '.');
        }
        return matrix;
    }
}
