/*Problem

        Design a stack that supports:

        push, pop, top, getMin → all in O(1)
        🔑 Key Idea — Stack + Track Minimum

        Use:

        Main stack
        Min stack (stores minimum so far)
        🚀 Java Solution

 */



import java.util.*;

class MinStack {

    Stack<Integer> stack;
    Stack<Integer> minStack;

    public MinStack() {
        stack = new Stack<>();
        minStack = new Stack<>();
    }

    public void push(int val) {
        stack.push(val);

        if (minStack.isEmpty() || val <= minStack.peek())
            minStack.push(val);
    }

    public void pop() {
        if (stack.pop().equals(minStack.peek()))
            minStack.pop();
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }
}



/*
🧠 Why this works
minStack keeps track of minimum at each level
Top of minStack = current minimum
Complexity
All operations → O(1)
Space → O(n)
⚡ Alternative (Single Stack Trick)

Store difference instead of actual values (advanced optimization).

 */