package com.example.exercise;

public class JumpGameIII {
    public boolean canReach(int[] arr, int start) {
        if (arr == null || arr.length == 0 || start < 0 || start >= arr.length) {
            throw new IllegalArgumentException();
        }

        int n = arr.length;
        boolean[] visited = new boolean[n];

        // Find a path that contains all positions, start already in path
        return canReach(arr, visited, start);
    }

    private boolean canReach(int[] arr, boolean[] visited, int cur) {
        // Base case 1: out of bound
        if (cur < 0 || cur >= arr.length) {
            return false;
        }
        // Base case 2: already visited
        if (visited[cur]) {
            return false;
        }
        // Base case 3: reach 0
        if (arr[cur] == 0) {
            return true;
        }

        visited[cur] = true;

        // Option 1: try cur - arr[cur]
        if (canReach(arr, visited, cur - arr[cur])) {
            return true;
        }
        // Option 2: try cur + arr[cur]
        if (canReach(arr, visited, cur + arr[cur])) {
            return true;
        }

        visited[cur] = false;

        return false;
    }
}
