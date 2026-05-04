/*(LeetCode 225)

        🔑 Key Idea — Make Push Costly (or Pop Costly)

        👉 Use one queue
        Maintain LIFO order by rotating elements

        🚀 Java Solution (One Queue)

 */





import java.util.*;

class MyStack {

    Queue<Integer> queue;

    public MyStack() {
        queue = new LinkedList<>();
    }

    public void push(int x) {

        queue.add(x);

        // rotate previous elements
        int size = queue.size();

        for (int i = 0; i < size - 1; i++) {
            queue.add(queue.remove());
        }
    }

    public int pop() {
        return queue.remove();
    }

    public int top() {
        return queue.peek();
    }

    public boolean empty() {
        return queue.isEmpty();
    }
}




/*
🧠 Why this works
Rotate queue so newest element comes to front
→ behaves like stack
        Complexity
Push : O(n)
Pop  : O(1)
Top  : O(1)
⚡ Alternative
Use 2 queues
Make pop costly instead
🔥 Interview Summary
Problem	Pattern
Min Stack	Auxiliary Stack
Stack using Queue	Data Structure Simulation
💡 Key Takeaways
Min Stack
Maintain min separately → O(1) access
Stack using Queue
Reorder queue → simulate LIFO
⚡ Pattern Recognition
Pattern	Use Case
Auxiliary DS	track min/max
Simulation	stack ↔ queue conversions

 */