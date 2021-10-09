package com.example.exercise;

// LC 531
public class LonelyPixel {
    public int findLonelyPixel(char[][] picture) {
        int rows = picture.length, cols = picture[0].length;

        int[] rowUsed = new int[rows];
        int[] colUsed = new int[cols];

        int count = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (picture[i][j] == 'B') {
                    rowUsed[i]++;
                    colUsed[j]++;
                }
            }
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (picture[i][j] == 'B' && rowUsed[i] == 1 && colUsed[j] == 1) {
                    count++;
                }
            }
        }

        return count;
    }
}
