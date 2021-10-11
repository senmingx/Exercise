package com.example.exercise;

import java.util.*;

public class WordLadder {
    // Assumptions:
    // 1. endWord.length == beginWord.length, beginWord != endWord
    // 2. beginWord, endWord, and wordList[i] consist of lowercase English letters.
    // 3. 1 <= wordList.length <= 5000, wordList[i].length == beginWord.length
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> dict = new HashSet<>(wordList);
        // Corner case: endWord is not in wordList
        if (!dict.contains(endWord)) {
            return 0;
        }

        // BFS from beginWord,
        // Each time change one char in current word, if exist in dict, then add to queue
        // Until endWord is found

        // Init ladder=1 because beginWord is included
        int ladder = 1;

        Queue<String> q = new LinkedList<>();
        q.offer(beginWord);

        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                String cur = q.poll();
                List<String> nextWords = convert(cur, dict);

                for (String next : nextWords) {
                    if (next.equals(endWord)) {
                        return ladder + 1;
                    }
                    q.offer(next);
                    // Remove from dict to avoid recurring visit
                    dict.remove(next);
                }
            }
            ladder++;
        }

        // Case: search all possibilities and can't find a path
        return 0;
    }

    private List<String> convert(String cur, Set<String> dict) {
        List<String> words = new ArrayList<>();
        char[] arr = cur.toCharArray();
        for (int i = 0; i < arr.length; i++) {
            char tmp = arr[i];
            for (char c = 'a'; c <= 'z'; c++) {
                if (c != tmp) {
                    arr[i] = c;
                    String newWord = new String(arr);
                    // When new word is in dict, add to words
                    if (dict.contains(newWord)) words.add(new String(arr));
                }
            }
            arr[i] = tmp;
        }
        return words;
    }
}
