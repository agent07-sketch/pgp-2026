/*Single Element in a Sorted Array

        (LeetCode 540)

Problem

In a sorted array:

Every element appears twice

Except one element that appears once

Find that single element in O(log n).

Example:

Input:  [1,1,2,3,3,4,4,8,8]
Output: 2
Key Binary Search Insight

Pairs follow a pattern:

Normal pair positions:

index: 0 1 | 2 3 | 4 5 | 6 7
value: a a | b b | c c | d d

First index of pair is even.

But after the single element appears, the pattern breaks.

So we check:

        if nums[mid] == nums[mid ^ 1]

mid ^ 1 flips:

        0 → 1
        1 → 0
        2 → 3
        3 → 2

 */

class Solution {
    public int singleNonDuplicate(int[] nums) {

        int low = 0;
        int high = nums.length - 1;

        while(low < high){

            int mid = low + (high - low) / 2;

            if(nums[mid] == nums[mid ^ 1])
                low = mid + 1;
            else
                high = mid;
        }

        return nums[low];
    }
}

/*
Complexity
Time  : O(log n)
Space : O(1)
 */