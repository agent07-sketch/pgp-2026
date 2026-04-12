/*Problem

Print numbers from n → 1 without using loops.

🔑 Key Idea — Recursion
Print current number
Call function for n-1

 */


class Solution {

    public void printNto1(int n) {

        if (n == 0)
            return;

        System.out.print(n + " ");

        printNto1(n - 1);
    }
}

/*
Example
Input: 5
Output: 5 4 3 2 1
Complexity
Time  : O(n)
Space : O(n)  (recursion stack)
        🔥 Interview Summary
Problem	Pattern
Fibonacci	DP / Iterative
Print n to 1	Recursion
💡 Key Takeaways
Fibonacci
Use iteration → best
Use DP → if recursion needed
Print n to 1
Recursion = loop replacement
⚡ Interview Insight
When to use Recursion?

If problem says:

no loop allowed

👉 Think:

Recursion

 */