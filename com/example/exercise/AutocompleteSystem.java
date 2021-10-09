package com.example.exercise;

import java.util.*;

public class AutocompleteSystem {

    private MyTrie trie;
    private StringBuilder input;

    public AutocompleteSystem(String[] sentences, int[] times) {
        trie = new MyTrie();
        for (int i = 0; i < sentences.length; i++) {
            trie.addSentence(sentences[i], times[i]);
        }
        input = new StringBuilder();
    }

    public List<String> input(char c) {
        if (c == '#') {
            trie.addSentence(input.toString());
            input.setLength(0);
            return new ArrayList<>();
        }
        // Append c to input, then returns only 3 top hot sentence
        input.append(c);
        return trie.getSentencesByPrefix(input.toString());
    }

    public static void main(String[] args) {
        String[] initSentence = new String[] {"i love you", "island", "iroman", "i love leetcode"};
        int[] initCount = new int[] {5,3,2,2};
        AutocompleteSystem system = new AutocompleteSystem(initSentence, initCount);

        System.out.println(system.input('i'));
        System.out.println(system.input(' '));
        System.out.println(system.input('a'));
        System.out.println(system.input('#'));
        System.out.println(system.input('i'));

        Queue<Integer> pq = new PriorityQueue<>();
        pq.offer(1);
        pq.offer(2);
        pq.offer(3);
        pq.offer(4);
        while (!pq.isEmpty()) {
            System.out.println(pq.poll());
        }
    }
}

class MyTrie {

    private MyTrieNode root;

    public MyTrie() {
        root = new MyTrieNode();
    }

    public void addSentence(String sentence) {
        addSentence(sentence, 1);
    }

    public void addSentence(String sentence, int time) {
        MyTrieNode cur = root;
        for (int i = 0; i < sentence.length(); i++) {
            char ch = sentence.charAt(i);
            MyTrieNode next = cur.children.get(ch);
            if (next == null) {
                next = new MyTrieNode();
                cur.children.put(ch, next);
            }
            cur = next;
        }
        // Last MyTrieNode set times
        cur.count += time;
    }

    public List<String> getSentencesByPrefix(String prefix) {
        List<Sentence> sentences = new ArrayList<>();

        MyTrieNode cur = root;
        for (int i = 0; i < prefix.length(); i++) {
            char ch = prefix.charAt(i);
            MyTrieNode next = cur.children.get(ch);
            if (next == null) {
                return new ArrayList<>();
            }
            cur = next;
        }

        StringBuilder path = new StringBuilder(prefix);
        getAllSentences(cur, path, sentences);

        return top3HotSentences(sentences);
    }

    // TODO: minHeap to keep only 3 highest hot degree
//    private List<String> top3HotSentences(List<Sentence> sentences) {
//        List<String> top3 = new ArrayList<>();
//        Collections.sort(sentences);
//
////        System.out.println(sentences);
//        for (int i = 0; i < Math.min(3, sentences.size()); i++) {
//            top3.add(sentences.get(i).content);
//        }
//        return top3;
//    }

    private List<String> top3HotSentences(List<Sentence> sentences) {
        Queue<Sentence> minHeap = new PriorityQueue<>();

        for (Sentence sentence : sentences) {
            if (minHeap.size() < 3) {
                minHeap.offer(sentence);
            } else if (sentence.compareTo(minHeap.peek()) > 0) {
                minHeap.poll();
                minHeap.offer(sentence);
            }
        }

        List<String> top3 = new ArrayList<>();
        while (!minHeap.isEmpty()) {
            top3.add(minHeap.poll().content);
        }
        Collections.reverse(top3);
        return top3;
    }

    private void getAllSentences(MyTrieNode node, StringBuilder path, List<Sentence> sentences) {
        // When found a sentence, add to list,
        // But don't return here, because its children may still have sentences
        if (node.count > 0) {
            sentences.add(new Sentence(path.toString(), node.count));
        }

        // Keep traversing children to find sentences
        for (char ch : node.children.keySet()) {
            MyTrieNode child = node.children.get(ch);

            path.append(ch);
            getAllSentences(child, path, sentences);
            path.setLength(path.length() - 1);
        }
    }

    private class MyTrieNode {
        Map<Character, MyTrieNode> children;
        int count;

        public MyTrieNode() {
            this(0);
        }

        public MyTrieNode(int c) {
            children = new HashMap<>();
            count = c;
        }
    }

    private class Sentence implements Comparable<Sentence> {
        String content;
        int count;

        public Sentence(String s, int c) {
            content = s;
            count = c;
        }

        @Override
        public int compareTo(Sentence o) {
            // If both has the same hot degrees, use ASCII-code order
            if (count == o.count) {
                return o.content.compareTo(content);
            }
            // Higher hot degree comes first
            return count - o.count;
        }

        @Override
        public String toString() {
            return content + ":" + count;
        }
    }
}
