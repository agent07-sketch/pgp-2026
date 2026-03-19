/*
Problem

        Sort elements:

        By frequency (ascending)

        If same frequency → value descending

        Example:

        Input:  [1,1,2,2,2,3]
        Output: [3,1,1,2,2,2]
        🔑 Approach

        Count frequency using HashMap

        Convert to list

        Sort with custom comparator
 */


import java.util.*;

class Solution {
    public int[] frequencySort(int[] nums) {

        Map<Integer, Integer> freq = new HashMap<>();

        // count frequency
        for (int num : nums) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }

        // convert to Integer array for sorting
        Integer[] arr = new Integer[nums.length];
        for (int i = 0; i < nums.length; i++) {
            arr[i] = nums[i];
        }

        // custom sort
        Arrays.sort(arr, (a, b) -> {
            if (freq.get(a).equals(freq.get(b))) {
                return b - a;  // value descending
            }
            return freq.get(a) - freq.get(b); // frequency ascending
        });

        // convert back
        for (int i = 0; i < nums.length; i++) {
            nums[i] = arr[i];
        }

        return nums;
    }
}

/*
Complexity
Time  : O(n log n)
Space : O(n)

 */