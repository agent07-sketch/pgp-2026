/*Problem

You have bags of balls. In one operation, you can split a bag into two bags.

Find the minimum possible maximum number of balls in any bag after at most maxOperations.

        Example:

nums = [9]
maxOperations = 2

Output = 3
        🔑 Key Idea — Binary Search on Answer

Instead of searching for a bag:

Search for the minimum valid penalty (max balls per bag)

Let:

mid = candidate maximum balls allowed

Check:

Can all bags be reduced so that
every bag <= mid
within maxOperations?
        🧠 Operations Needed

For a bag with balls:

operations = (balls - 1) / mid

Example:

balls = 9, mid = 3

        9 → 3,3,3

operations = (9-1)/3 = 2
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

            if (canAchieve(nums, maxOperations, mid)) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }

        return ans;
    }

    private boolean canAchieve(int[] nums, int maxOperations, int penalty) {

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

Pattern Recognition:
Minimize maximum
Maximize minimum
Find smallest valid answer

=> Binary Search on Answer

 */