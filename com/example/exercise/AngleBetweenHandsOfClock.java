package com.example.exercise;

public class AngleBetweenHandsOfClock {
    public double angleClock(int hour, int minutes) {
        // Corner case
        if (hour < 1 || hour > 12 || minutes < 0 || minutes > 59) {
            throw new IllegalArgumentException();
        }

        int oneHourAngle = 30;
        int oneMinuteAngle = 6;

        double hourAngle = (hour % 12 + minutes / 60.0) * oneHourAngle;
        double minuteAngle = minutes * oneMinuteAngle;

        double diff = Math.abs(hourAngle - minuteAngle);
        return Math.min(diff, 360 - diff);
    }
}
