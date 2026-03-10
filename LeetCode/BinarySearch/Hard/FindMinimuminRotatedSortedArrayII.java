/*
Goal: Return minimum element

Example

Input:  [2,2,2,0,1]
Output: 0
Key Idea

Compare with high.

Cases:

nums[mid] > nums[high]
→ minimum in right side

nums[mid] < nums[high]
→ minimum in left side

nums[mid] == nums[high]
→ cannot decide → shrink high--
 */


class Solution {
    public int findMin(int[] nums) {

        int low = 0;
        int high = nums.length - 1;

        while (low < high) {

            int mid = low + (high - low) / 2;

            if (nums[mid] > nums[high])
                low = mid + 1;

            else if (nums[mid] < nums[high])
                high = mid;

            else
                high--;
        }

        return nums[low];
    }
}