/*Problem

Rotate array to the right by k steps.

Example:

Input:  nums = [1,2,3,4,5,6,7], k = 3
Output: [5,6,7,1,2,3,4]
        🔑 Optimal Idea — Reversal Algorithm

Steps:

        1. Reverse entire array
2. Reverse first k elements
3. Reverse remaining elements
 */


class Solution {
    public void rotate(int[] nums, int k) {

        int n = nums.length;
        k = k % n;

        reverse(nums, 0, n - 1);
        reverse(nums, 0, k - 1);
        reverse(nums, k, n - 1);
    }

    private void reverse(int[] nums, int left, int right) {

        while (left < right) {
            int temp = nums[left];
            nums[left] = nums[right];
            nums[right] = temp;
            left++;
            right--;
        }
    }
}
/*
Complexity
Time  : O(n)
Space : O(1)

 */