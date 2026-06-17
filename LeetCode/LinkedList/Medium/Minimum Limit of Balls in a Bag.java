/*Problem

Given bags containing balls and at most maxOperations splits, minimize the maximum number of balls in any bag.

        Example:

nums = [9]
maxOperations = 2

Output = 3
        🔑 Key Idea — Binary Search on Answer

Instead of searching indices, search the answer:

Can every bag be made ≤ mid ?

For a bag with balls:

operations needed = (balls - 1) / mid
🚀 Java Solution

 */







class Solution {

    public int minimumSize(int[] nums, int maxOperations) {

        int low = 1;
        int high = 0;

        for (int num : nums)
            high = Math.max(high, num);

        int ans = high;

        while (low <= high) {

            int mid = low + (high - low) / 2;

            if (canSplit(nums, maxOperations, mid)) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return ans;
    }

    private boolean canSplit(int[] nums,
                             int maxOperations,
                             int penalty) {

        long operations = 0;

        for (int balls : nums) {
            operations += (balls - 1) / penalty;

            if (operations > maxOperations)
                return false;
        }

        return true;
    }
}






/*
Complexity
Time  : O(n log(max(nums)))
Space : O(1)
Pattern Recognition
Minimize maximum
→ Binary Search on Answer

 */