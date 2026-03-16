/*
Problem

Given an array nums, find the k-th smallest absolute difference between any pair.

Example:

nums = [1,3,1]
k = 1

Pairs:

(1,1) → 0
(1,3) → 2
(1,3) → 2

Sorted distances:

0,2,2

Answer:

0
Key Idea (Binary Search on Distance)

Range of answer:

low = 0
high = max(nums) - min(nums)

For each mid distance:

Count how many pairs have distance ≤ mid.

If:

count >= k

→ answer is ≤ mid

Otherwise:

increase distance
Efficient Pair Counting (Two Pointer)

Because array is sorted.

nums[j] - nums[i] <= mid

Move j forward and count pairs.
 */

import java.util.*;

class Solution {

    public int smallestDistancePair(int[] nums, int k) {

        Arrays.sort(nums);

        int low = 0;
        int high = nums[nums.length-1] - nums[0];

        while(low < high){

            int mid = low + (high - low) / 2;

            int count = countPairs(nums, mid);

            if(count >= k)
                high = mid;
            else
                low = mid + 1;
        }

        return low;
    }

    private int countPairs(int[] nums, int dist){

        int count = 0;
        int left = 0;

        for(int right = 0; right < nums.length; right++){

            while(nums[right] - nums[left] > dist)
                left++;

            count += right - left;
        }

        return count;
    }
}