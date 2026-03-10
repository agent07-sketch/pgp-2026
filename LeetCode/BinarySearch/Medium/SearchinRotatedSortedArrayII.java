/*
 Search in Rotated Sorted Array II

Goal: Return true if target exists

Key Idea

In a rotated sorted array with duplicates:

Normally we detect sorted half using:

nums[low] <= nums[mid]

But duplicates cause ambiguity like:

[1,0,1,1,1]

So when:

nums[low] == nums[mid] == nums[high]

We shrink the search space.
*/
class Solution {
    public boolean search(int[] nums, int target) {

        int low = 0;
        int high = nums.length - 1;

        while (low <= high) {

            int mid = low + (high - low) / 2;

            if (nums[mid] == target)
                return true;

            // handle duplicates
            if (nums[low] == nums[mid] && nums[mid] == nums[high]) {
                low++;
                high--;
            }

            // left half sorted
            else if (nums[low] <= nums[mid]) {

                if (nums[low] <= target && target < nums[mid])
                    high = mid - 1;
                else
                    low = mid + 1;
            }

            // right half sorted
            else {

                if (nums[mid] < target && target <= nums[high])
                    low = mid + 1;
                else
                    high = mid - 1;
            }
        }

        return false;
    }
}