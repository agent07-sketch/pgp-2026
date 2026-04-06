/*Problem

Find max subarray sum in circular array.

        Example:

Input:  [5,-3,5]
Output: 10
        🔑 Key Idea

Two cases:

Case 1: Normal Kadane
maxSubarraySum
Case 2: Circular case
totalSum - minSubarraySum
⚠️ Edge Case

If all elements are negative:

        return maxSubarraySum

 */


class Solution {
    public int maxSubarraySumCircular(int[] nums) {

        int total = 0;

        int maxSum = nums[0];
        int currMax = 0;

        int minSum = nums[0];
        int currMin = 0;

        for (int num : nums) {

            currMax = Math.max(num, currMax + num);
            maxSum = Math.max(maxSum, currMax);

            currMin = Math.min(num, currMin + num);
            minSum = Math.min(minSum, currMin);

            total += num;
        }

        // all negative case
        if (maxSum < 0)
            return maxSum;

        return Math.max(maxSum, total - minSum);
    }
}

/*
Complexity
Time  : O(n)
Space : O(1)
🔥 Interview Summary
Problem	Pattern
Swapping Pairs	Math + HashSet
Circular Subarray	Kadane + Trick
💡 Key Takeaways
Swapping Pairs
Reduce problem to:
a - b = diff/2
Circular Subarray
Answer = max(
        normal Kadane,
        total - min Kadane
)
⚡ Interview Insight
Kadane Variants
Problem	Trick
Max Subarray	Kadane
Max Circular	Kadane + total - min
Max Product	track max & min
 */