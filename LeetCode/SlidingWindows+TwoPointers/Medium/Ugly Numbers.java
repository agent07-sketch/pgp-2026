/*Problem

Find the n-th ugly number
        (Ugly numbers = only prime factors {2,3,5})

Example:

Input: n = 10 → Output = 12
        🔑 Key Idea — DP with 3 Pointers

Maintain:

next multiples of 2, 3, 5
        🚀 Java Solution

 */




class Solution {
    public int nthUglyNumber(int n) {

        int[] dp = new int[n];
        dp[0] = 1;

        int i2 = 0, i3 = 0, i5 = 0;

        for (int i = 1; i < n; i++) {

            int next2 = dp[i2] * 2;
            int next3 = dp[i3] * 3;
            int next5 = dp[i5] * 5;

            dp[i] = Math.min(next2, Math.min(next3, next5));

            if (dp[i] == next2) i2++;
            if (dp[i] == next3) i3++;
            if (dp[i] == next5) i5++;
        }

        return dp[n - 1];
    }
}



/*
Complexity
Time  : O(n)
Space : O(n)
🧠 Why this works
Build sequence in sorted order
Avoid duplicates using multiple pointers
🔥 Interview Summary
Problem	Pattern
Longest Substring K Distinct	Sliding Window
Ugly Numbers	DP + Multiple Pointers
💡 Key Takeaways
K Distinct Substring
map.size() ≤ k → valid window
Ugly Numbers
Generate numbers in order using 2,3,5 pointers
⚡ Pattern Recognition
Pattern	When to Use
Sliding Window	substring + constraint
DP + pointers	sequence generation

 */