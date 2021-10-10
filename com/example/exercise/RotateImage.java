package com.example.exercise;

public class RotateImage {
    // Assumptions:
    // 1. matrix.length == n, matrix[i].length == n, 1 <= n <= 20
    // 2. -1000 <= matrix[i][j] <= 1000
    public void rotate(int[][] matrix) {
        rotate(matrix, 0, matrix.length);
    }

    private void rotate(int[][] matrix, int offset, int size) {
        if (size <= 1) {
            return;
        }

        for (int i = 0; i < size - 1; i++) {
            // Cache upper row
            int tmp = matrix[offset][offset + i];
            // Upper row = left col
            matrix[offset][offset + i] = matrix[offset + size - 1 - i][offset];
            // Lect col = lower row
            matrix[offset + size - 1 - i][offset] = matrix[offset + size - 1][offset + size - 1 - i];
            // lower row = right col
            matrix[offset + size - 1][offset + size - 1 - i] = matrix[offset + i][offset + size - 1];
            // right col = upper row
            matrix[offset + i][offset + size - 1] = tmp;
        }

        rotate(matrix, offset + 1, size - 2);
    }
}
