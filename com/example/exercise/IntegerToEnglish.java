package com.example.exercise;

public class IntegerToEnglish {

    private static String[] DIGITS = {"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"};
    private static String[] TEN_TO_NITETEEN = {"Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    private static String[] TENS = {"", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};

    public String numberToWords(int num) {
        // Corner cases to consider
        // 1. num == 0
        // 2. space at the end of string when ends up with hundreds/thousand/million/billion
        if (num == 0) {
            return "Zero";
        }

        return numberToWordsHelper(num);
    }

    public String numberToWordsHelper(int num) {
        String numString;
        if (num < 10) {
            numString = DIGITS[num];
        } else if (num < 20) {
            numString = TEN_TO_NITETEEN[num - 10];
        } else if (num < 100) {
            numString = TENS[num / 10] + " " + DIGITS[num % 10];
        } else if (num < 1000) {
            // hundreds + below 100
            numString = DIGITS[num / 100] + " Hundred " + numberToWordsHelper(num % 100);
        } else if (num < 1000000) {
            // thousands + below thousand
            numString = numberToWordsHelper(num / 1000) + " Thousand " + numberToWordsHelper(num % 1000);
        } else if (num < 1000000000) {
            // millions + below million
            numString = numberToWordsHelper(num / 1000000) + " Million " + numberToWordsHelper(num % 1000000);
        } else { // From one billion to Integer.MAX_VALUE
            // billions + below billion
            numString = numberToWordsHelper(num / 1000000000) + " Billion " + numberToWordsHelper(num % 1000000000);
        }

        return numString.trim();
    }

    public static void main(String[] args) {
        IntegerToEnglish sol = new IntegerToEnglish();
        System.out.println(sol.numberToWords(100));
        System.out.println(sol.numberToWords(12345));
        System.out.println(sol.numberToWords(1234567));
        System.out.println(sol.numberToWords(1234567891));
    }
}
