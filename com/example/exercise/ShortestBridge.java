package com.example.exercise;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

// LC 934
public class ShortestBridge {

    private static final int[][] DIRS = new int[][] {{1,0}, {0,1}, {-1,0}, {0,-1}};

    public int shortestBridge(int[][] grid) {
        // 1. DFS from one '1' and mark all traversed grids (one island) to '2'
        // 2. BFS level order from all '2' to find '1', the first time we find '1' we get the shortest distance

        int m = grid.length, n = grid[0].length;
        Queue<int[]> queue = new LinkedList<>();

        boolean traverseOneIsland = false;
        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++) {
                if (!traverseOneIsland && grid[row][col] == 1) {
                    traverse(grid, row, col, queue);
                    traverseOneIsland = true;
                }
            }
        }

        return shortestBridge(grid, queue);
    }

    private int shortestBridge(int[][] grid, Queue<int[]> queue) {
        // shortestBridge = shortest distance - 1

        int m = grid.length, n = grid[0].length;
        int shortestBridge = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                // Expand
                int[] cur = queue.poll();
                for (int[] dir : DIRS) {
                    int nextX = cur[0] + dir[0];
                    int nextY = cur[1] + dir[1];
                    if (nextX >= 0 && nextX < m && nextY >= 0 && nextY < n && grid[nextX][nextY] != 2) {
                        if (grid[nextX][nextY] == 1) {
                            return shortestBridge;
                        }
                        grid[nextX][nextY] = 2;
                        // Generate
                        queue.offer(new int[] {nextX, nextY});
                    }
                }
            }
            shortestBridge++;
        }

        return -1;
    }

    private void traverse(int[][] grid, int row, int col, Queue<int[]> queue) {
        // Base case
        int m = grid.length, n = grid[0].length;
        if (row < 0 || row >= m || col < 0 || col >= n || grid[row][col] != 1) {
            return;
        }

        grid[row][col] = 2;
        queue.offer(new int[] {row, col});

        for (int[] dir : DIRS) {
            traverse(grid, row + dir[0], col + dir[1], queue);
        }
    }

    private void printGrid(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(grid[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        ShortestBridge sol = new ShortestBridge();

        int[][] grid1 = new int[][] {{0,1},{1,0}};
        int[][] grid2 = new int[][] {{0,1,0},{0,0,0},{0,0,1}};
        int[][] grid3 = new int[][] {{1,1,1,1},{1,0,0,1},{1,0,1,0,1},{1,0,0,0,1},{1,1,1,1,1}};

        assertEquals(1, sol.shortestBridge(grid1));
        assertEquals(2, sol.shortestBridge(grid2));
        assertEquals(1, sol.shortestBridge(grid3));
    }
}
