package com.example.exercise;

import java.util.Stack;

public class MinStack {

    private Stack<Integer> data;
    private Stack<Integer> min;

    public MinStack() {
        data = new Stack<>();
        min = new Stack<>();
    }

    public void push(int val) {
        data.push(val);
        if (min.isEmpty() || val <= min.peek()) {
            min.push(val);
        }
    }

    // Assumption: stack not empty
    public void pop() {
        // Remember to use equals rather than ==
        if (data.peek().equals(min.peek())) {
            min.pop();
        }
        data.pop();
    }

    // Assumption: stack not empty
    public int top() {
        return data.peek();
    }

    // Assumption: stack not empty
    public int getMin() {
        return min.peek();
    }

}
