package com.example.exercise;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FindMin {

    public int findMin(int[] A) {
        int ans = 0;
        for (int i = 1; i < A.length; i++) {
            if (ans > A[i]) {
                ans = A[i];
            }
        }
        return ans;
    }

    public int findCorrectMin(int[] A) {
        int ans = A[0];
        for (int i = 1; i < A.length; i++) {
            if (ans > A[i]) {
                ans = A[i];
            }
        }
        return ans;
    }

    public int[] solution(int N) {
        if (N < 0) {
            throw new IllegalArgumentException();
        }

        // counterexample
        int[] arr = new int[N];
        arr[0] = -1;

        Arrays.fill(arr, 1, arr.length, 1);
        printArray(arr);

        return arr;
    }

    private void printArray(int[] arr) {
        for (int num : arr) {
            System.out.print(num + ",");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        FindMin fm = new FindMin();

        int[] nums = fm.solution(5);
        assertEquals(fm.findCorrectMin(nums), fm.findMin(nums));
    }
}
