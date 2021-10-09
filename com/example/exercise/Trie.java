package com.example.exercise;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Trie {

    private TrieNode root;

    public Trie() {
        root = new TrieNode(false);
    }

    public void insert(String word) {
        // For each char in word
        // 1. Get cur's child by char, if not exist, create one
        // 2. Set cur = child

        TrieNode cur = root;
        for (int i = 0; i < word.length(); i++) {
            TrieNode child = cur.children.get(word.charAt(i));
            if (child == null) {
                child = new TrieNode(false);
                cur.children.put(word.charAt(i), child);
            }
            cur = child;
        }
        cur.isWord = true;
    }

    public boolean search(String word) {
        TrieNode node = getPrefix(word);
        return node != null && node.isWord;
    }

    public boolean startsWith(String prefix) {
        // 1. Find the TrieNode corresponding to prefix
        // 2. DFS from the node to see if any child has isWord = true

        TrieNode prefixNode = getPrefix(prefix);
        return prefixNode != null && hasAnyWord(prefixNode);
    }

    private boolean hasAnyWord(TrieNode prefixNode) {
        // Base case: Found one word
        if (prefixNode.isWord) {
            return true;
        }

        for (Character childChar : prefixNode.children.keySet()) {
            if (hasAnyWord(prefixNode.children.get(childChar))) {
                return true;
            }
        }

        // Traverse all children but can not find any word
        return false;
    }

    private TrieNode getPrefix(String prefix) {
        TrieNode cur = root;

        for (int i = 0; i < prefix.length(); i++) {
            TrieNode child = cur.children.get(prefix.charAt(i));
            if (child == null) {
                return null;
            }
            cur = child;
        }

        return cur;
    }

    public static void main(String[] args) {
        Trie trie = new Trie();

        trie.insert("apple");
        assertTrue(trie.search("apple"));
        assertFalse(trie.search("app"));
        assertTrue(trie.startsWith("app"));
        trie.insert("app");
        assertTrue(trie.search("app"));

    }


    private class TrieNode {
        // TrieNode's children
        Map<Character, TrieNode> children;
        // TrieNode indicates a word
        boolean isWord;
        int frequency;

        public TrieNode(boolean isWord) {
            children = new HashMap<>();
            this.isWord = isWord;
        }
    }
}
