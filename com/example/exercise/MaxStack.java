package com.example.exercise;

import javafx.util.Pair;

import java.util.Stack;

public class MaxStack {

    private Stack<Integer> dataStack;
    private Stack<Integer> maxStack;

    public MaxStack() {
        dataStack = new Stack<>();
        maxStack = new Stack<>();
    }

    public void push(int x) {
        dataStack.push(x);
        if (maxStack.isEmpty() || x >= maxStack.peek()) {
            maxStack.push(x);
        }
    }

    // Assume stack has at least one element
    public int pop() {
        int pop = dataStack.pop();
        if (maxStack.peek().equals(pop)) {
            maxStack.pop();
        }
        return pop;
    }

    // Assume stack has at least one element
    public int top() {
        return dataStack.peek();
    }

    // Assume stack has at least one element
    public int peekMax() {
        return maxStack.peek();
    }

    // Assume stack has at least one element
    public int popMax() {
        Stack<Integer> tmp = new Stack<>();
        while (!dataStack.peek().equals(maxStack.peek())) {
            tmp.push(dataStack.pop());
        }
        int max = maxStack.pop();
        dataStack.pop();
        while (!tmp.isEmpty()) {
            push(tmp.pop());
        }
        return max;
    }
}
