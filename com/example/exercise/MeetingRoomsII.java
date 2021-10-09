package com.example.exercise;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MeetingRoomsII {
    public int minMeetingRooms(int[][] intervals) {
        if (intervals == null || intervals.length == 0) {
            return 0;
        }

        List<EndPoint> eps = new ArrayList<>();
        for (int[] interval : intervals) {
            eps.add(new EndPoint(interval[0], true));
            eps.add(new EndPoint(interval[1], false));
        }
        Collections.sort(eps, (ep1, ep2) -> {
            if (ep1.val == ep2.val) {
                return ep1.isStart ? 1 : -1;
            }
            return ep1.val - ep2.val;
        });

        int rooms = 0;
        int max = 0; // max number of rooms at the same time
        for (EndPoint ep : eps) {
            if (ep.isStart) {
                rooms++;
                max = Math.max(max, rooms);
            } else {
                rooms--;
            }
        }
        return max;
    }

    class EndPoint {
        int val;
        boolean isStart;

        public EndPoint(int v, boolean s) {
            val = v;
            isStart = s;
        }
    }
}
