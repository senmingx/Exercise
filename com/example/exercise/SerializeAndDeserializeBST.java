package com.example.exercise;

import javafx.util.Pair;

// LC 449
public class SerializeAndDeserializeBST {

    // Use preorder

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) {
            return "#";
        }

        StringBuilder sb = new StringBuilder();
        preorder(root, sb);

        // Remove last ','
        sb.setLength(sb.length() - 1);
        return sb.toString();
    }

    private void preorder(TreeNode root, StringBuilder sb) {
        if (root == null) {
            return;
        }

        sb.append(root.val + ",");
        preorder(root.left, sb);
        preorder(root.right, sb);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data.equals("#")) {
            return null;
        }

        String[] arr = data.split(",");
        return preorderDecode(arr, 0, arr.length - 1);
    }

    private TreeNode preorderDecode(String[] arr, int start, int end) {
        if (start > end) {
            return null;
        }
        if (start == end) {
            return new TreeNode(Integer.valueOf(arr[start]));
        }

        TreeNode root = new TreeNode(Integer.valueOf(arr[start]));

        // 1. Find the first greater than root.val in [start+1, end], say index
        // 2. [start+1, index-1] will be in left subtree, [index, end] will be in right subtree
        int firstLargerIndex = firstLargerIndex(arr, start + 1, end, root.val);
        if (firstLargerIndex > -1) {
            root.left = preorderDecode(arr, start + 1, firstLargerIndex - 1);
            root.right = preorderDecode(arr, firstLargerIndex, end);
        } else {
            root.left = preorderDecode(arr, start + 1, end);
        }

        return root;
    }

    private int firstLargerIndex(String[] arr, int start, int end, int target) {
        while (start + 1 < end) {
            int mid = start + (end - start) / 2;
            if (Integer.parseInt(arr[mid]) < target) { // Search right
                start = mid;
            } else { // Search left
                end = mid;
            }
        }

        if (Integer.parseInt(arr[start]) >= target) {
            return start;
        }
        if (Integer.parseInt(arr[end]) >= target) {
            return end;
        }
        return -1;
    }


    class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }
}
