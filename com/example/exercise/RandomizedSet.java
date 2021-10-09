package com.example.exercise;

import javafx.util.Pair;

import java.util.*;

// LC 380
public class RandomizedSet {

    Map<Integer, Integer> valToIndex;
    List<Integer> vals;

    public RandomizedSet() {
        valToIndex = new HashMap<>();
        vals = new ArrayList<>();
    }

    public boolean insert(int val) {
        // 1. Check if exist in map
        // 2. Add to both map and list
        if (valToIndex.containsKey(val)) {
            return false;
        }

        valToIndex.put(val, vals.size());
        // Always append to end of list
        vals.add(val);

        return true;
    }

    public boolean remove(int val) {
        // 1. Check if exist in map
        // 2. Swap val with last num in list, update index in map
        // 3. Remove from map and end of list
        if (!valToIndex.containsKey(val)) {
            return false;
        }

        int index = valToIndex.get(val);
        int lastIndex = vals.size() - 1;
        int lastVal = vals.get(lastIndex);

        vals.set(index, lastVal);
        valToIndex.put(lastVal, index);

        vals.remove(lastIndex);
        valToIndex.remove(val);

        return true;
    }


    public int getRandom() {
        return vals.get((int) (Math.random() * vals.size()));
    }
}
