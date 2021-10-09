package com.example.exercise;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DigitsIncrement {

    public int maxNum(int num, int k) {
        if (k < 0) {
            return num;
        }

        int hundred = num / 100;
        int ten = (num - hundred * 100) / 10;
        int digit = num % 10;

        if (hundred < 9) {
            if (hundred + k > 9) {
                k -= (9 - hundred);
                hundred = 9;
            } else {
                hundred += k;
                k = 0;
            }
        }
        if (k > 0 && ten < 9) {
            if (ten + k > 9) {
                k -= (9 - ten);
                ten = 9;
            } else {
                ten += k;
                k = 0;
            }
        }
        if (k > 0) {
            digit += k;
        }

        System.out.println(hundred * 100 + ten * 10 + digit);
        return hundred * 100 + ten * 10 + digit;
    }

    public static void main(String[] args) {
        DigitsIncrement di = new DigitsIncrement();

        assertEquals(998, di.maxNum(245, 15));
    }
}
