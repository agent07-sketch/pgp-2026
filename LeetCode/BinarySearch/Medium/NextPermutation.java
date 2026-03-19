/*
Problem:

Rearrange numbers into the next lexicographically greater permutation.
If not possible → return the smallest permutation (sorted ascending).

Example:

Input:  [1,2,3]
Output: [1,3,2]

Input:  [3,2,1]
Output: [1,2,3]
        🔑 Key Idea (3 Steps)

Find breakpoint (first decreasing from right)

nums[i] < nums[i+1]

Find just greater element on right side and swap

Reverse the suffix (make it smallest)

 */

class Solution {
    public void nextPermutation(int[] nums) {

        int n = nums.length;
        int i = n - 2;

        // Step 1: find breakpoint
        while (i >= 0 && nums[i] >= nums[i + 1]) {
            i--;
        }

        // Step 2: find next greater element and swap
        if (i >= 0) {
            int j = n - 1;
            while (nums[j] <= nums[i]) {
                j--;
            }
            swap(nums, i, j);
        }

        // Step 3: reverse the suffix
        reverse(nums, i + 1, n - 1);
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private void reverse(int[] nums, int start, int end) {
        while (start < end) {
            swap(nums, start++, end--);
        }
    }
}

/*
Complexity
Time  : O(n)
Space : O(1)

 */