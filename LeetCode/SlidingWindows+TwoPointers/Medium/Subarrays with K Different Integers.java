/*Problem

        Count subarrays with exactly K distinct integers.

        Example:

        nums = [1,2,1,2,3], k = 2 → Output = 7
        🔑 Key Trick
        Exactly(K) = AtMost(K) - AtMost(K-1)
        🚀 Java Solution

 */


import java.util.*;

class Solution {

    public int subarraysWithKDistinct(int[] nums, int k) {
        return atMost(nums, k) - atMost(nums, k - 1);
    }

    private int atMost(int[] nums, int k) {

        Map<Integer, Integer> map = new HashMap<>();
        int left = 0, count = 0;

        for (int right = 0; right < nums.length; right++) {

            map.put(nums[right], map.getOrDefault(nums[right], 0) + 1);

            while (map.size() > k) {
                map.put(nums[left], map.get(nums[left]) - 1);
                if (map.get(nums[left]) == 0)
                    map.remove(nums[left]);
                left++;
            }

            count += right - left + 1;
        }

        return count;
    }
}

/*
Complexity
Time  : O(n)
Space : O(k)
🧠 Insight
Counting exact K directly is hard
→ convert to AtMost trick

 */