/*Problem

Find the maximum product of a contiguous subarray.

Example:

Input:  [2,3,-2,4]
Output: 6
        🔑 Key Idea — Track BOTH max & min

Why?

Negative × Negative = Positive

So:

Track maxProd
Track minProd

 */


class Solution {
    public int maxProduct(int[] nums) {

        int max = nums[0];
        int min = nums[0];
        int ans = nums[0];

        for (int i = 1; i < nums.length; i++) {

            int curr = nums[i];

            if (curr < 0) {
                int temp = max;
                max = min;
                min = temp;
            }

            max = Math.max(curr, max * curr);
            min = Math.min(curr, min * curr);

            ans = Math.max(ans, max);
        }

        return ans;
    }
}

/*
Complexity
Time  : O(n)
Space : O(1)
🔥 Interview Summary
Problem	Pattern
Find Duplicates	Index Marking
Max Product Subarray	Kadane Variant (max + min)
💡 Key Takeaways
Find Duplicates
value → index mapping
sign = visited
Max Product Subarray
Always track:
max product
min product
⚡ Interview Trick
When to use Max & Min?

If problem involves:

multiplication + negative numbers

👉 Always track both.

 */