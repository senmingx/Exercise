package com.example.exercise;

import java.util.*;

public class GroupAnagrams {
    // Assumption: strs[i] consists of lowercase letters
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> groups = new ArrayList<>();
        // Corner cases
        if (strs == null) {
            throw new IllegalArgumentException();
        }
        if (strs.length == 0) {
            return groups;
        }

        // Key: sorted string, value: anagrams of key
        Map<String, List<String>> groupMap = new HashMap<>();

        // Each time meet a word, sort it first, if exist in map, then add word
        // to that group, otherwise create new group and add it
        for (String str : strs) {
            String sorted = sortString(str);

            List<String> group = groupMap.get(sorted);
            if (group == null) {
                group = new ArrayList<>();
                groupMap.put(sorted, group);
            }
            group.add(str);
        }

        for (String key : groupMap.keySet()) {
            groups.add(groupMap.get(key));
        }

        return groups;
    }

    // Count sort: O(k),  is length of str
    private String sortString(String str) {
        int[] count = new int[26];
        for (int i = 0; i < str.length(); i++) {
            count[str.charAt(i) - 'a']++;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count.length; i++) {
            if (count[i] > 0) {
                for (int j = 0; j < count[i]; j++) sb.append((char)('a' + i));
            }
        }

        return sb.toString();
    }
}
