/*Problem

Return the n-th Fibonacci number:

F(0)=0
F(1)=1
F(n)=F(n-1)+F(n-2)
🔑 Approach 1 — Iterative (Best)

 */


class Solution {
    public int fib(int n) {

        if (n <= 1) return n;

        int prev2 = 0; // F(0)
        int prev1 = 1; // F(1)

        for (int i = 2; i <= n; i++) {
            int curr = prev1 + prev2;
            prev2 = prev1;
            prev1 = curr;
        }

        return prev1;
    }
}
Complexity
Time  : O(n)
Space : O(1)
⚡ Alternative — DP (Memoization)
class Solution {
    public int fib(int n) {
        int[] dp = new int[n + 1];
        return solve(n, dp);
    }

    private int solve(int n, int[] dp) {
        if (n <= 1) return n;

        if (dp[n] != 0) return dp[n];

        return dp[n] = solve(n - 1, dp) + solve(n - 2, dp);
    }
}

/*
⚠️ Avoid (Naive Recursion)
O(2^n) → TLE

 */