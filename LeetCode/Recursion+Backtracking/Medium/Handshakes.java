/*Problem

Given n people (even), count ways to form non-crossing handshakes.

Example:

n = 4 → Output = 2
n = 6 → Output = 5
        🔑 Key Idea — Catalan Numbers

Let:

pairs = n / 2

Then:

dp[i] = Σ dp[j] * dp[i-j-1]
 */


class Solution {

    public int countWays(int n) {

        int pairs = n / 2;

        long[] dp = new long[pairs + 1];
        dp[0] = 1;
        dp[1] = 1;

        for (int i = 2; i <= pairs; i++) {

            for (int j = 0; j < i; j++) {
                dp[i] += dp[j] * dp[i - j - 1];
            }
        }

        return (int) dp[pairs];
    }
}


/*
Complexity
Time  : O(n^2)
Space : O(n)
🧠 Why this works
Fix one handshake → splits into left + right subproblems
🔥 Interview Summary
Problem	Pattern
Rat Maze (multi-jump)	Backtracking
Handshakes	Catalan / DP
💡 Key Takeaways
Rat Maze
Try all possible jumps
Backtrack on failure
        Handshakes
Same as:
        - Valid parentheses
- BST count
⚡ Pattern Recognition
Backtracking Problems

If:

find path / generate solutions

👉 Use recursion + try all moves

Catalan Problems

If:

non-crossing / valid combinations

👉 Use Catalan formula

 */