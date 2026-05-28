/*Problem

Find max of:

        (nums[i] - 1) * (nums[j] - 1)
        🔑 Core Idea

👉 We just need two largest numbers

⚡ Approach

Track:

max1 (largest)
max2 (second largest)
✅ Java Code (Optimal)

 */





class Solution {

    public int maxProduct(int[] nums) {

        int max1 = 0, max2 = 0;

        for (int num : nums) {

            if (num > max1) {
                max2 = max1;
                max1 = num;
            } else if (num > max2) {
                max2 = num;
            }
        }

        return (max1 - 1) * (max2 - 1);
    }
}





/*
🔍 Example
nums = [3,4,5,2]

max1 = 5
max2 = 4

        (5-1)*(4-1) = 4*3 = 12
        ⏱ Complexity
Time: O(n)
Space: O(1)
🚨 Mistakes

❌ Sorting (unnecessary O(n log n))
        ❌ Not tracking second max properly
❌ Edge case: small array

🧠 Pattern Summary
Problem	Pattern
Non-overlapping Intervals	Greedy (sort by end)
Max Product	Two max elements

 */