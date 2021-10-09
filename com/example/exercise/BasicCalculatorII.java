package com.example.exercise;

import java.util.Stack;

// LC 227
public class BasicCalculatorII {
    public int calculate(String s) {
        Stack<Integer> stack = new Stack<>();
        char operator = '+';
        int currNum = 0;

        // 4 + 3 * 6 - 10
        //              i
        //
        // st: 4 18 -10
        // operator: -
        // curNum: 10
        //
        // res: -10 + 18 + 4 = 12

        for (int i = 0; i < s.length(); i++) {
            char cur = s.charAt(i);
            if (Character.isWhitespace(cur)) {
                continue;
            }
            if (Character.isDigit(cur)) {
                currNum = currNum * 10 + (cur - '0');
            }
            if (!Character.isDigit(cur) || i == s.length() - 1) {
                if (operator == '+') {
                    stack.push(currNum);
                } else if (operator == '-') {
                    stack.push(-currNum);
                } else if (operator == '*') {
                    stack.push(stack.pop() * currNum);
                } else if (operator == '/') {
                    stack.push(stack.pop() / currNum);
                }
                currNum = 0;
                operator = cur;
            }
        }

        int res = 0;
        while (!stack.isEmpty()) {
            res += stack.pop();
        }

        return res;
    }
}
