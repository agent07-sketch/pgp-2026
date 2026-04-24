/*Problem

        Count subarrays whose sum = goal (array contains only 0 and 1).

        Example:

        nums = [1,0,1,0,1], goal = 2
        Output = 4
        🔑 Approach 1 — Prefix Sum + HashMap (Most intuitive)
        Idea

        If:

        currentSum - goal = previousSum

        → we found a valid subarray.

        🚀 Java Solution
 */


import java.util.*;

class Solution {
    public int numSubarraysWithSum(int[] nums, int goal) {

        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);

        int sum = 0, count = 0;

        for (int num : nums) {

            sum += num;

            if (map.containsKey(sum - goal))
                count += map.get(sum - goal);

            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }

        return count;
    }
}



/*
⚡ Approach 2 — Sliding Window (AtMost Trick)
Exactly(goal) = AtMost(goal) - AtMost(goal - 1)
Java Solution
 */




class Solution {

    public int numSubarraysWithSum(int[] nums, int goal) {
        return atMost(nums, goal) - atMost(nums, goal - 1);
    }

    private int atMost(int[] nums, int goal) {

        if (goal < 0) return 0;

        int left = 0, sum = 0, count = 0;

        for (int right = 0; right < nums.length; right++) {

            sum += nums[right];

            while (sum > goal) {
                sum -= nums[left++];
            }

            count += right - left + 1;
        }

        return count;
    }
}

/*
Complexity
Time  : O(n)
Space : O(n) (prefix) / O(1) (sliding window)
        🧠 Key Insight
Binary array → sliding window works because sum increases monotonically

 */