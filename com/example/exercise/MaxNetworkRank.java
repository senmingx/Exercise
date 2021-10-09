package com.example.exercise;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MaxNetworkRank {

    // Time: O(n^2)
    // Space: O(n^2)
    public int maximalNetworkRank(int n, int[][] roads) {
        // corner case
        if (n < 0 || roads == null) {
            throw new IllegalArgumentException();
        }

        // 1. counts number of roads each city has
        // 2. For each pair of city, rank = sum of roads if not connected, otherwise sum of roads
        // 3. Get the pair with max rank

        int[] counts = new int[n];
        boolean[][] isConnected = new boolean[n][n];

        for (int[] road : roads) {
            counts[road[0]]++;
            counts[road[1]]++;
            isConnected[road[0]][road[1]] = true;
            isConnected[road[1]][road[0]] = true;
        }

        // First and second largest number of roads - O(n)
        int first = 0;
        int second = 0;
        for (int count : counts) {
            if (count > first) {
                second = first;
                first = count;
            } else if (count == first) {
                continue;
            } else if (count > second) {
                second = count;
            }
        }

        // Number of cities has first or largest number of roads - O(n)
        int firstCities = 0;
        int secondCities = 0;
        for (int i = 0; i < n; i++) {
            if (counts[i] == first) {
                firstCities++;
            } else if (counts[i] == second) {
                secondCities++;
            }
        }

        // O(n)
        // Case 1: More than 1 cities has first number of roads
        //         For each road, if both cities has first number of roads, calculate rank

        // Case 2: Only 1 city has first number of roads
        //         For each road, if one city has first number of roads, the other has second number of roads,
        //         calculate rank

        System.out.println(first + ":" + firstCities + "," + second + ":" + secondCities);
        System.out.println(isConnected[5][2]);

        int maxRank = 0;

        if (firstCities > 1) {
            for (int[] road : roads) {
                if (counts[road[0]] == first && counts[road[1]] == first) {
                    int rank = first + first;
                    if (isConnected[road[0]][road[1]]) {
                        rank -= 1;
                    }
                    maxRank = Math.max(maxRank, rank);
                }
            }
        } else {
            for (int[] road : roads) {
                if (counts[road[0]] == first && counts[road[1]] == second
                        || counts[road[0]] == second && counts[road[1]] == first) {
                    int rank = first + second;
                    if (isConnected[road[0]][road[1]]) {
                        rank -= 1;
                    }
                    maxRank = Math.max(maxRank, rank);
                }
            }
        }

        return maxRank;
    }

    public int maximalNetworkRank1(int n, int[][] roads) {
        // corner case
        if (n < 0 || roads == null) {
            throw new IllegalArgumentException();
        }

        // 1. counts number of roads each city has
        // 2. For each pair of city, rank = sum of roads if not connected, otherwise sum of roads
        // 3. Get the pair with max rank

        int[] counts = new int[n];
        boolean[][] isConnected = new boolean[n][n];

        for (int[] road : roads) {
            counts[road[0]]++;
            counts[road[1]]++;
            isConnected[road[0]][road[1]] = true;
            isConnected[road[1]][road[0]] = true;
        }

        int maxRank = 0;
        for (int city1 = 0; city1 < n; city1++) {
            for (int city2 = city1 + 1; city2 < n; city2++) {
                int rank = counts[city1] + counts[city2];
                if (isConnected[city1][city2]) {
                    rank -= 1;
                }
                maxRank = Math.max(maxRank, rank);
            }
        }

        return maxRank;
    }

    public static void main(String[] args) {
        MaxNetworkRank sol = new MaxNetworkRank();

//        assertEquals(4, sol.maximalNetworkRank(4, new int[][] {{0,1}, {0,3}, {1,2}, {1,3}}));
//        assertEquals(5, sol.maximalNetworkRank(5, new int[][] {{0,1}, {0,3}, {1,2}, {1,3}, {2,3}, {2,4}}));
        assertEquals(5, sol.maximalNetworkRank(8, new int[][] {{0,1}, {1,2}, {2,3}, {2,4}, {5,6}, {5,7}}));
    }
}
