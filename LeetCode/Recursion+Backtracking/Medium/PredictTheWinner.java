/*Problem

Two players pick numbers from ends.
Return true if Player 1 can win.

        🔑 Key Idea — Game Theory + DP

We compute:

max score difference current player can achieve
🧠 Recurrence
pick left  → nums[left] - opponent result
pick right → nums[right] - opponent result
🚀 Java Solution (Memoization)

 */


class Solution {

    public boolean PredictTheWinner(int[] nums) {

        int n = nums.length;
        Integer[][] dp = new Integer[n][n];

        return solve(nums, 0, n - 1, dp) >= 0;
    }

    private int solve(int[] nums, int left, int right, Integer[][] dp) {

        if (left == right)
            return nums[left];

        if (dp[left][right] != null)
            return dp[left][right];

        int pickLeft = nums[left] - solve(nums, left + 1, right, dp);
        int pickRight = nums[right] - solve(nums, left, right - 1, dp);

        return dp[left][right] = Math.max(pickLeft, pickRight);
    }
}

/*
Complexity
Time  : O(n^2)
Space : O(n^2)
🧠 Why this works
We simulate optimal play for both players
🔥 Interview Summary
Problem	Pattern
Permutations II	Backtracking + Duplicate Handling
Predict the Winner	DP + Game Theory
💡 Key Takeaways
Permutations II
Sort + used[] + skip duplicates
Predict the Winner
Think in terms of score difference
current player vs opponent
⚡ Pattern Recognition
Backtracking Problems
Permutations
        Subsets
Combination Sum

👉 Use recursion + state tracking

Game Theory Problems
Pick from ends
Two players
Optimal play

👉 Use DP + recursion

 */