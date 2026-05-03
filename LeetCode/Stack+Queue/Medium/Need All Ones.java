/*(Flip at most K zeros to make all ones)

Problem

Find longest subarray where we can flip at most k zeros → all 1s.

🔑 Key Idea — Sliding Window

Maintain:

number of zeros ≤ k
🚀 Java Solution

 */




class Solution {
    public int longestOnes(int[] nums, int k) {

        int left = 0, zeros = 0, maxLen = 0;

        for (int right = 0; right < nums.length; right++) {

            if (nums[right] == 0)
                zeros++;

            while (zeros > k) {
                if (nums[left] == 0)
                    zeros--;
                left++;
            }

            maxLen = Math.max(maxLen, right - left + 1);
        }

        return maxLen;
    }
}



/*
🧠 Why this works
Allow k zeros → treat them as ones
Keep window valid → maximize length
Complexity
Time  : O(n)
Space : O(1)
🔥 Interview Summary
Problem	Pattern
Celebrity Problem	Elimination + Verification
Need All Ones	Sliding Window
💡 Key Takeaways
Celebrity Problem
Eliminate candidates first → verify later
Need All Ones
At most k invalid elements → sliding window
⚡ Pattern Recognition
Pattern	Use Case
Elimination	candidate finding problems
Sliding Window	longest subarray with constraint
🚀 Final Insight

If problem says:

        "find special person satisfying conditions"

        👉 Think:

Elimination strategy

 */