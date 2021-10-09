package com.example.exercise;

import java.util.*;

public class KillProcess {
    public List<Integer> killProcess(List<Integer> pid, List<Integer> ppid, int kill) {
        // Corner case
        if (pid == null || ppid == null || pid.size() != ppid.size()) {
            throw new IllegalArgumentException();
        }

        // Key: parent's process ID, value: process ID of parent process
        Map<Integer, List<Integer>> childrenMap = new HashMap<>();
        for (int i = 0; i < pid.size(); i++) {
            int parent = ppid.get(i);
            int child = pid.get(i);
            List<Integer> children = childrenMap.get(parent);
            if (children == null) {
                children = new ArrayList<>();
                childrenMap.put(parent, children);
            }
            children.add(child);
        }

        // DFS from kill to find all its children
        List<Integer> killed = new ArrayList<>();
        killProcess(kill, childrenMap, killed);

        return killed;
    }

    private void killProcess(int kill, Map<Integer, List<Integer>> childrenMap, List<Integer> killed) {
        killed.add(kill);

        if (childrenMap.get(kill) == null) {
            return;
        }

        for (Integer child : childrenMap.get(kill)) {
            killProcess(child, childrenMap, killed);
        }
    }
}
