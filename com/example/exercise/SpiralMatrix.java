package com.example.exercise;

import java.util.*;

public class SpiralMatrix {
    public List<Integer> spiralOrder(int[][] matrix) {
        // Corner - skipped

        List<Integer> res = new ArrayList<>();
        int m = matrix.length, n = matrix[0].length;

        spiralOrder(matrix, 0, m, n, res);

        return res;
    }

    // offset: start position (offset, offset)
    // rows: num of rows in cur loop
    // cols: num of cols in cur loop
    private void spiralOrder(int[][] matrix, int offset, int rows, int cols, List<Integer> res) {
        // Base case 1: nothing left
        if (rows <= 0 || cols <= 0) {
            return;
        }
        // Base case 2: one row left
        if (rows == 1) {
            for (int i = 0; i < cols; i++) {
                res.add(matrix[offset][offset + i]);
            }
            return;
        }
        // Base case 3: one col left
        if (cols == 1) {
            for (int i = 0; i < rows; i++) {
                res.add(matrix[offset + i][offset]);
            }
            return;
        }

        // 1. Add cur loop
        // Upper row
        for (int i = 0; i < cols - 1; i++) {
            res.add(matrix[offset][offset + i]);
        }
        // Right col
        for (int i = 0; i < rows - 1; i++) {
            res.add(matrix[offset + i][offset + cols - 1]);
        }
        // Lower row
        for (int i = 0; i < cols - 1; i++) {
            res.add(matrix[offset + rows - 1][offset + cols - 1 - i]);
        }
        // Left col
        for (int i = 0; i < rows - 1; i++) {
            res.add(matrix[offset + rows - 1 - i][offset]);
        }

        // 2. Add inner loops
        spiralOrder(matrix, offset + 1, rows - 2, cols - 2, res);
    }

    public static void main(String[] args) {
        test(1,1);
    }

    private static void test(int rows, int cols) {
        SpiralMatrix sol = new SpiralMatrix();
        int[][] orig = matrix(rows, cols);
        printMatrix(orig);
        System.out.println(sol.spiralOrder(orig));
    }

    private static int[][] matrix(int rows, int cols) {
        int[][] matrix = new int[rows][cols];
        int num = 1;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = num++;
            }
        }

        return matrix;
    }

    private static void printMatrix(int[][] matrix) {
        int rows = matrix.length, cols = matrix[0].length;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(matrix[i][j] + "\t");
            }
            System.out.println();
        }
        System.out.println();
    }
}
