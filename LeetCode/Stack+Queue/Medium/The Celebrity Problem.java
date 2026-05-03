/*Problem

In a party:

Celebrity knows no one
Everyone knows the celebrity

Find the celebrity index or return -1.

        🔑 Key Idea — Elimination (Two Pointers)

👉 If A knows B → A cannot be celebrity
👉 Else → B cannot be celebrity

🚀 Java Solution (Optimal O(n))

 */




class Solution {

    // knows(a, b) API assumed
    boolean knows(int a, int b) {
        return true; // placeholder
    }

    public int findCelebrity(int n) {

        int candidate = 0;

        // Step 1: find candidate
        for (int i = 1; i < n; i++) {
            if (knows(candidate, i))
                candidate = i;
        }

        // Step 2: verify candidate
        for (int i = 0; i < n; i++) {

            if (i == candidate) continue;

            if (knows(candidate, i) || !knows(i, candidate))
                return -1;
        }

        return candidate;
    }
}




/*
🧠 Why this works
We eliminate non-celebrities in one pass
Then verify only one candidate
        Complexity
Time  : O(n)
Space : O(1)

 */