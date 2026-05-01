/*Problem

        Support operations:

        1 x → push x
        2   → pop
        3   → print max
        🔑 Key Idea — Stack + Track Max

        Use:

        Main stack
        Max stack (store max till that point)
        🚀 Java Solution

 */



import java.util.*;

class MaxStack {

    Stack<Integer> stack = new Stack<>();
    Stack<Integer> maxStack = new Stack<>();

    public void push(int x) {
        stack.push(x);

        if (maxStack.isEmpty() || x >= maxStack.peek())
            maxStack.push(x);
    }

    public void pop() {
        if (stack.pop().equals(maxStack.peek()))
            maxStack.pop();
    }

    public int getMax() {
        return maxStack.peek();
    }
}



/*
🧠 Why this works
maxStack always stores current maximums
        Top = current max
        Complexity
Push : O(1)
Pop  : O(1)
Max  : O(1)
🔥 Interview Summary
Problem	Pattern
Largest Rectangle	Monotonic Stack
Maximum Element	Stack + Auxiliary Stack
💡 Key Takeaways
Histogram Problem
Use monotonic increasing stack
Compute area when stack breaks
Max Stack
Maintain max separately → O(1) queries
⚡ Pattern Recognition
Pattern	Use Case
Monotonic Stack	NSE, histogram, rainwater
Auxiliary Stack	Track min/max
🚀 Final Insight

If problem says:

next greater / smaller
largest area
range boundary

👉 Think:

Monotonic Stack

 */