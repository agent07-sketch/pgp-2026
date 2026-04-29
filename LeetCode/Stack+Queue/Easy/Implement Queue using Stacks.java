/*Problem

        Implement queue using only stacks:

        push, pop, peek, empty
        🔑 Key Idea — Two Stacks

        Use:

        inStack → for push
        outStack → for pop
        🚀 Java Solution
 */



import java.util.*;

class MyQueue {

    Stack<Integer> inStack;
    Stack<Integer> outStack;

    public MyQueue() {
        inStack = new Stack<>();
        outStack = new Stack<>();
    }

    public void push(int x) {
        inStack.push(x);
    }

    public int pop() {
        move();
        return outStack.pop();
    }

    public int peek() {
        move();
        return outStack.peek();
    }

    public boolean empty() {
        return inStack.isEmpty() && outStack.isEmpty();
    }

    private void move() {
        if (outStack.isEmpty()) {
            while (!inStack.isEmpty()) {
                outStack.push(inStack.pop());
            }
        }
    }
}




/*
🧠 Why this works
inStack → stores order
outStack → reverses for FIFO
        Complexity
Amortized:
Time  : O(1)
Space : O(n)
🔥 Interview Summary
Problem	Pattern
Last Stone Weight	Heap / Priority Queue
Queue using Stacks	Stack Simulation
💡 Key Takeaways
Heap Problems
Repeated min/max extraction → use heap
Queue via Stacks
Reverse order using second stack
⚡ Pattern Recognition
Pattern	Use Case
Heap	Top K / largest elements
Stack	Order reversal / simulation

 */