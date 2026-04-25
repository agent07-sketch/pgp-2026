/*Problem

Flip at most k zeros → maximize consecutive 1s.

        Example:

nums = [1,1,1,0,0,0,1,1,1,1,0], k=2 → Output = 6
        🔑 Key Idea — Sliding Window

We maintain:

number of zeros ≤ k
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
🧠 Insight
Flip at most k zeros → allow k invalid elements
Complexity
Time  : O(n)
Space : O(1)
🔥 Interview Summary
Problem	Pattern
Character Replacement	Sliding Window + maxFreq
Max Consecutive Ones III	Sliding Window + zero count
💡 Key Takeaways
Character Replacement
window - maxFreq ≤ k
Max Consecutive Ones
zeros ≤ k
⚡ Pattern Recognition (VERY IMPORTANT)

These belong to same family:

        "Longest subarray/substring with at most K changes"
        🧠 General Template
while (invalid condition) {
shrink window
}
        🚀 Final Insight
Problem Type	What to Track
Replace chars	max frequency
Flip bits	count of zeros
Distinct chars	hashmap size

 */