/*Problem

        Find indices of two numbers whose sum equals target.

        Example:

        nums = [2,7,11,15]
        target = 9

        Output = [0,1]
        🔑 Key Idea — HashMap

        For every number:

        complement = target - num

        If complement already exists → answer found.

        🚀 Java Solution

 */


import java.util.*;

class Solution {
    public int[] twoSum(int[] nums, int target) {

        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {

            int complement = target - nums[i];

            if (map.containsKey(complement)) {
                return new int[]{
                        map.get(complement),
                        i
                };
            }

            map.put(nums[i], i);
        }

        return new int[]{};
    }
}

/*
Complexity
Time  : O(n)
Space : O(n)
If Array is Already Sorted

Use Two Pointers:

        int left = 0, right = nums.length - 1;

while(left < right){

int sum = nums[left] + nums[right];

    if(sum == target)
        return true;
        else if(sum < target)
left++;
        else
right--;
        }

 */