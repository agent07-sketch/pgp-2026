/*(Catalan Number Problem)

Problem

Given n pairs of parentheses, count valid combinations.

        Example:

n = 3
Output = 5

Valid:

        ((())), (()()), (())(), ()(()), ()()()
        🔑 Key Idea — Catalan Numbers

Formula:

C(n) = (2n)! / ((n+1)! * n!)
        🧠 DP Approach (Interview Friendly)
dp[n] = Σ dp[i] * dp[n-1-i]

 */



class Solution {
    public int countValid(int n) {

        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;

        for (int i = 2; i <= n; i++) {

            for (int j = 0; j < i; j++) {
                dp[i] += dp[j] * dp[i - j - 1];
            }
        }

        return dp[n];
    }
}

/*
Complexity
Time  : O(n^2)
Space : O(n)
🔥 Interview Insight

Same pattern used in:

Unique BSTs
Parentheses generation
Binary tree structures

 */